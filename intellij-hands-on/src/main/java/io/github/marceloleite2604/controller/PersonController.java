package io.github.marceloleite2604.controller;

import io.github.marceloleite2604.model.server.ServerResponse;
import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.validation.HttpPostValidationGroup;
import io.github.marceloleite2604.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
public class PersonController {

    private final PersonService personService;

    @Inject
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(SitePaths.PERSON)
    public String getPerson(Model model) {
        return personService.getPerson(model);
    }

    @GetMapping({SitePaths.PERSON_NEW, SitePaths.PERSON + "/{id}"})
    public String getPersonEdit(Model model, @PathVariable(name = "id", required = false) String id) {
        return personService.getPersonEdit(model, id);
    }

    @PostMapping(SitePaths.PERSON)
    public String postPerson(Model model, @Validated(HttpPostValidationGroup.class) @ModelAttribute("person") PersonDto personDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        return personService.postPerson(model, personDto, bindingResult, redirectAttributes);
    }

    @DeleteMapping(path = SitePaths.PERSON + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerResponse<String>> deletePerson(@PathVariable(name = "id", required = true) String id) {
        return personService.deletePerson(id);
    }
}
