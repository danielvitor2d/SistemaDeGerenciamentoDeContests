package gemp.github.controller;

import gemp.github.dto.model.team.TeamDTO;
import gemp.github.dto.response.Response;
import gemp.github.model.team.Team;
import gemp.github.model.team.Team;
import gemp.github.service.team.TeamService;
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
@RequestMapping("/team-manager/v1/team")
public class TeamController{
    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Team>>> get(){
        Response<List<Team>> response = new Response<>();
        response.setData(teamService.findAll());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Response<TeamDTO>> create(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                       @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody TeamDTO dto, BindingResult result) {

        Response<TeamDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }


        Team team = dto.convertDTOToEntity();
        Team teamToCreate = teamService.save(team);

        TeamDTO dtoSaved = teamToCreate.convertEntityToDTO();
        createSelfLink(teamToCreate, dtoSaved);

        response.setData(dtoSaved);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<TeamDTO>> update(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                       @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody TeamDTO dto, BindingResult result)
            throws Exception {

        Response<TeamDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Team teamToFind = teamService.findById(dto.getId());

        if (teamToFind.getId().compareTo(dto.getId()) != 0) {
            throw new Exception("You don't have permission to change the team id =" + dto.getId());
        }

        Team team = dto.convertDTOToEntity();
        Team teamToUpdate = teamService.save(team);

        TeamDTO itemDTO = teamToUpdate.convertEntityToDTO();
        createSelfLink(teamToUpdate, itemDTO);
        response.setData(itemDTO);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<TeamDTO>> findById(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                                 String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @PathVariable("id") Long teamId,
                                                         @RequestParam(required = false) String fields) throws Exception {

        Response<TeamDTO> response = new Response<>();
        Team team = teamService.findById(teamId);

        TeamDTO dto = team.convertEntityToDTO();

        createSelfLink(team, dto);
        response.setData(dto);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                           String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
                                                   @PathVariable("id") Long teamId) throws Exception {

        Response<String> response = new Response<>();
        Team team = teamService.findById(teamId);

        teamService.deleteById(team.getId());
        response.setData("Team id =" + team.getId() + " successfully deleted");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.NO_CONTENT);
    }

    private void createSelfLink(Team team, TeamDTO teamDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(TeamController.class).slash(team.getId()).withSelfRel();
        teamDTO.add(selfLink);
    }
}