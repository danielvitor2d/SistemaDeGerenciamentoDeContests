package gemp.github.controller;

import gemp.github.dto.model.contest.ContestDTO;
import gemp.github.dto.model.person.PersonDTO;
import gemp.github.dto.response.Response;
import gemp.github.model.contest.Contest;
import gemp.github.model.person.Person;
import gemp.github.model.person.Person;
import gemp.github.service.contest.ContestService;
import gemp.github.service.person.PersonService;
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
@RequestMapping("/contest-manager/v1/person")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Person>>> get() {
        Response<List<Person>> response = new Response<>();
        response.setData(personService.findAll());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Response<PersonDTO>> create(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                      @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody PersonDTO dto, BindingResult result) {

        Response<PersonDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }


        Person person = dto.convertDTOToEntity();
        Person personToCreate = personService.save(person);

        PersonDTO dtoSaved = personToCreate.convertEntityToDTO();
        createSelfLink(personToCreate, dtoSaved);

        response.setData(dtoSaved);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response<PersonDTO>> update(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
                                                      @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @Valid @RequestBody PersonDTO dto, BindingResult result)
            throws Exception {

        Response<PersonDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Person personToFind = personService.findById(dto.getId());

        if (personToFind.getId().compareTo(dto.getId()) != 0) {
            throw new Exception("You don't have permission to change the person id =" + dto.getId());
        }

        Person person = dto.convertDTOToEntity();
        Person personToUpdate = personService.save(person);

        PersonDTO itemDTO = personToUpdate.convertEntityToDTO();
        createSelfLink(personToUpdate, itemDTO);
        response.setData(itemDTO);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<PersonDTO>> findById(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                                    String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey, @PathVariable("id") Long personId,
                                                        @RequestParam(required = false) String fields) throws Exception {

        Response<PersonDTO> response = new Response<>();
        Person person = personService.findById(personId);

        PersonDTO dto = person.convertEntityToDTO();

        createSelfLink(person, dto);
        response.setData(dto);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@RequestHeader(value = ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, defaultValue = "${api.version}")
                                                           String apiVersion, @RequestHeader(value = ContestManagerUtil.HEADER_API_KEY, defaultValue = "${api.key}") String apiKey,
                                                   @PathVariable("id") Long personId) throws Exception {

        Response<String> response = new Response<>();
        Person person = personService.findById(personId);

        personService.deleteById(person.getId());
        response.setData("Person id =" + person.getId() + " successfully deleted");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(ContestManagerUtil.HEADER_CONTESTMANAGER_API_VERSION, apiVersion);
        headers.add(ContestManagerUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.NO_CONTENT);
    }

    private void createSelfLink(Person person, PersonDTO personDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(PersonController.class).slash(person.getId()).withSelfRel();
        personDTO.add(selfLink);
    }
}