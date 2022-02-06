package gemp.github.controller;

import gemp.github.dto.model.contest.ContestDTO;
import gemp.github.dto.model.problem.ProblemDTO;
import gemp.github.dto.response.Response;
import gemp.github.model.contest.Contest;
import gemp.github.model.problem.Problem;
import gemp.github.model.problem.Problem;
import gemp.github.service.contest.ContestService;
import gemp.github.service.problem.ProblemService;
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
@RequestMapping("/contest-manager/v1/problem")
public class ProblemController {
    private ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Problem>>> get() {
        Response<List<Problem>> response = new Response<>();
        response.setData(problemService.findAll());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Response<ProblemDTO>> create(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                       @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody ProblemDTO dto, BindingResult result) {

        Response<ProblemDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }


        Problem problem = dto.convertDTOToEntity();
        Problem problemToCreate = problemService.save(problem);

        ProblemDTO dtoSaved = problemToCreate.convertEntityToDTO();
        createSelfLink(problemToCreate, dtoSaved);

        response.setData(dtoSaved);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<ProblemDTO>> update(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                       @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody ProblemDTO dto, BindingResult result)
            throws Exception {

        Response<ProblemDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Problem problemToFind = problemService.findById(dto.getId());

        if (problemToFind.getId().compareTo(dto.getId()) != 0) {
            throw new Exception("You don't have permission to change the problem id =" + dto.getId());
        }

        Problem problem = dto.convertDTOToEntity();
        Problem problemToUpdate = problemService.save(problem);

        ProblemDTO itemDTO = problemToUpdate.convertEntityToDTO();
        createSelfLink(problemToUpdate, itemDTO);
        response.setData(itemDTO);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<ProblemDTO>> findById(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                                     String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @PathVariable("id") Long problemId,
                                                         @RequestParam(required = false) String fields) throws Exception {

        Response<ProblemDTO> response = new Response<>();
        Problem problem = problemService.findById(problemId);

        ProblemDTO dto = problem.convertEntityToDTO();

        createSelfLink(problem, dto);
        response.setData(dto);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                           String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
                                                   @PathVariable("id") Long problemId) throws Exception {

        Response<String> response = new Response<>();
        Problem problem = problemService.findById(problemId);

        problemService.deleteById(problem.getId());
        response.setData("Problem id =" + problem.getId() + " successfully deleted");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.NO_CONTENT);
    }

    private void createSelfLink(Problem problem, ProblemDTO problemDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(ProblemController.class).slash(problem.getId()).withSelfRel();
        problemDTO.add(selfLink);
    }
}