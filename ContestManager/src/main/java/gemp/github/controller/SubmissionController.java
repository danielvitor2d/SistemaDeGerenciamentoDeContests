package gemp.github.controller;

import gemp.github.dto.model.contest.ContestDTO;
import gemp.github.dto.model.submission.SubmissionDTO;
import gemp.github.dto.response.Response;
import gemp.github.model.contest.Contest;
import gemp.github.model.submission.Submission;
import gemp.github.model.submission.Submission;
import gemp.github.service.contest.ContestService;
import gemp.github.service.submission.SubmissionService;
import gemp.github.service.submission.SubmissionService;
import gemp.github.util.ContestManagerUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/contest-manager/v1/submission")
public class SubmissionController {
    private SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Submission>>> get() {
        Response<List<Submission>> response = new Response<>();
        response.setData(submissionService.findAll());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Response<SubmissionDTO>> create(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                          @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody SubmissionDTO dto, BindingResult result) {

        Response<SubmissionDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }


        Submission submission = dto.convertDTOToEntity();
        Submission submissionToCreate = submissionService.save(submission);

        SubmissionDTO dtoSaved = submissionToCreate.convertEntityToDTO();
        createSelfLink(submissionToCreate, dtoSaved);

        response.setData(dtoSaved);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<SubmissionDTO>> update(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                          @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody SubmissionDTO dto, BindingResult result)
            throws Exception {

        Response<SubmissionDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Submission submissionToFind = submissionService.findById(dto.getId());

        if (submissionToFind.getId().compareTo(dto.getId()) != 0) {
            throw new Exception("You don't have permission to change the submission id =" + dto.getId());
        }

        Submission submission = dto.convertDTOToEntity();
        Submission submissionToUpdate = submissionService.save(submission);

        SubmissionDTO itemDTO = submissionToUpdate.convertEntityToDTO();
        createSelfLink(submissionToUpdate, itemDTO);
        response.setData(itemDTO);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<SubmissionDTO>> findById(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                                        String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @PathVariable("id") Long submissionId,
                                                            @RequestParam(required = false) String fields) throws Exception {

        Response<SubmissionDTO> response = new Response<>();
        Submission submission = submissionService.findById(submissionId);

        SubmissionDTO dto = submission.convertEntityToDTO();

        createSelfLink(submission, dto);
        response.setData(dto);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                           String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
                                                   @PathVariable("id") Long submissionId) throws Exception {

        Response<String> response = new Response<>();
        Submission submission = submissionService.findById(submissionId);

        submissionService.deleteById(submission.getId());
        response.setData("Submission id =" + submission.getId() + " successfully deleted");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.NO_CONTENT);
    }

    private void createSelfLink(Submission submission, SubmissionDTO submissionDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(SubmissionController.class).slash(submission.getId()).withSelfRel();
        submissionDTO.add(selfLink);
    }
}