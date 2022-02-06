package gemp.github.controller;

import gemp.github.dto.model.contest.ContestDTO;
import gemp.github.dto.response.Response;
import gemp.github.model.contest.Contest;
import gemp.github.service.contest.ContestService;
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
@RequestMapping("/contest-manager/v1/contest")
public class ContestController {
    private ContestService contestService;

    @Autowired
    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Contest>>> get(){
        Response<List<Contest>> response = new Response<>();
        response.setData(contestService.findAll());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Response<ContestDTO>> create(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                       @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody ContestDTO dto, BindingResult result) {

        Response<ContestDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }


        Contest contest = dto.convertDTOToEntity();
        Contest contestToCreate = contestService.save(contest);

        ContestDTO dtoSaved = contestToCreate.convertEntityToDTO();
        createSelfLink(contestToCreate, dtoSaved);

        response.setData(dtoSaved);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<ContestDTO>> update(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                      @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody ContestDTO dto, BindingResult result)
            throws Exception {

        Response<ContestDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Contest contestToFind = contestService.findById(dto.getId());

        if (contestToFind.getId().compareTo(dto.getId()) != 0) {
            throw new Exception("You don't have permission to change the contest id =" + dto.getId());
        }

        Contest contest = dto.convertDTOToEntity();
        Contest contestToUpdate = contestService.save(contest);

        ContestDTO itemDTO = contestToUpdate.convertEntityToDTO();
        createSelfLink(contestToUpdate, itemDTO);
        response.setData(itemDTO);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<ContestDTO>> findById(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                                String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @PathVariable("id") Long contestId,
                                                        @RequestParam(required = false) String fields) throws Exception {

        Response<ContestDTO> response = new Response<>();
        Contest contest = contestService.findById(contestId);

        ContestDTO dto = contest.convertEntityToDTO();

        createSelfLink(contest, dto);
        response.setData(dto);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                           String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
                                                   @PathVariable("id") Long contestId) throws Exception {

        Response<String> response = new Response<>();
        Contest contest = contestService.findById(contestId);

        contestService.deleteById(contest.getId());
        response.setData("Contest id =" + contest.getId() + " successfully deleted");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.NO_CONTENT);
    }

    private void createSelfLink(Contest contest, ContestDTO contestDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(ContestController.class).slash(contest.getId()).withSelfRel();
        contestDTO.add(selfLink);
    }
}