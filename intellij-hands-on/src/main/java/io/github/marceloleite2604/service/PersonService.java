package io.github.marceloleite2604.service;

import io.github.marceloleite2604.bo.PersonBo;
import io.github.marceloleite2604.controller.SitePaths;
import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.message.ErrorMessage;
import io.github.marceloleite2604.model.message.SiteMessage;
import io.github.marceloleite2604.model.po.Person;
import io.github.marceloleite2604.model.server.ServerMessage;
import io.github.marceloleite2604.model.server.ServerMessageType;
import io.github.marceloleite2604.model.server.ServerResponse;
import io.github.marceloleite2604.util.MessageRetriever;
import io.github.marceloleite2604.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonBo personBo;

    private final ServiceUtil serviceUtil;

    private final MessageRetriever messageRetriever;

    @Inject
    public PersonService(PersonBo personBo, ServiceUtil serviceUtil, MessageRetriever messageRetriever) {
        this.personBo = personBo;
        this.serviceUtil = serviceUtil;
        this.messageRetriever = messageRetriever;
    }

    public String getPerson(Model model) {
        addPersonDtosOnModel(model);
        return TemplateLocations.PERSON;
    }

    private void addPersonDtosOnModel(Model model) {
        List<PersonDto> personDtos = retrievePersons();
        model.addAttribute(ThymeleafModelAttributeNames.PERSONS, personDtos);
    }

    private List<PersonDto> retrievePersons() {
        List<Person> persons = personBo.findAll();
        personBo.sortByName(persons);
        return personBo.getPersonPoToDtoMapper().mapAllToDto(persons);
    }

    public String getPersonEdit(Model model, String id) {
        addPersonDtoOnModel(model, id);
        return TemplateLocations.PERSON_EDIT;
    }

    private void addPersonDtoOnModel(Model model, String id) {
        PersonDto personDto = createOrRetrievePerson(id);
        model.addAttribute(ThymeleafModelAttributeNames.PERSON, personDto);
    }

    private PersonDto createOrRetrievePerson(String id) {
        return Optional.ofNullable(id)
                .map(this::retrievePerson)
                .orElseGet(PersonDto::new);
    }

    private PersonDto retrievePerson(String id) {
        Person person = personBo.findById(id);
        return personBo.getPersonPoToDtoMapper().mapToDto(person);
    }

    public String postPerson(Model model, PersonDto personDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(ThymeleafModelAttributeNames.PERSON, personDto);
            return TemplateLocations.PERSON_EDIT;
        }

        PersonDto savedPersonDto = personBo.save(personDto);
        addInformationMessage(redirectAttributes, personDto, savedPersonDto);

        return serviceUtil.redirectTo(SitePaths.PERSON);
    }

    private void addInformationMessage(RedirectAttributes redirectAttributes, PersonDto personDto, PersonDto savedPersonDto) {
        SiteMessage siteMessage = checkMessageToInform(personDto);
        serviceUtil.addInformationMessage(redirectAttributes, siteMessage, savedPersonDto.getName());
    }

    private SiteMessage checkMessageToInform(PersonDto personDto) {
        SiteMessage siteMessage;
        if (personBo.isNew(personDto)) {
            siteMessage = SiteMessage.PERSON_CREATED;
        } else {
            siteMessage = SiteMessage.PERSON_UPDATED;
        }
        return siteMessage;
    }

    public ResponseEntity<ServerResponse<String>> deletePerson(String id) {

        Optional<Person> optionalPerson;

        try {
            optionalPerson = personBo
                    .findOptionalById(id);
        } catch (Exception exception) {
            serviceUtil.logErrorMessage(LOGGER, exception, ErrorMessage.ERROR_DELETE_PERSON, id);
            return serviceUtil.createInternalServerErrorResponse(String.class, SiteMessage.COULD_NOT_DELETE_PERSON, id);
        }

        return optionalPerson
                .map(this::deletePerson)
                .orElseGet(() -> returnPersonNotFoundOnDelete(id));
    }

    private ResponseEntity<ServerResponse<String>> deletePerson(Person person) {
        try {
            personBo.delete(person);

            String message = messageRetriever.getMessage(SiteMessage.PERSON_DELETED, person.getName());

            ServerMessage serverMessage = ServerMessage.Builder
                    .aServerMessage()
                    .content(message)
                    .type(ServerMessageType.INFORMATION)
                    .build();

            ServerResponse<String> serverResponse = ServerResponse.Builder
                    .aServerResponse(String.class)
                    .message(serverMessage)
                    .build();

            return new ResponseEntity<>(serverResponse, HttpStatus.OK);
        } catch (Exception exception) {
            String logMessage = messageRetriever.getMessage(ErrorMessage.ERROR_DELETE_PERSON, person.getName());
            LOGGER.error(logMessage, exception);

            String message = messageRetriever.getMessage(SiteMessage.COULD_NOT_DELETE_PERSON, person.getName());

            ServerMessage serverMessage = ServerMessage.Builder
                    .aServerMessage()
                    .content(message)
                    .type(ServerMessageType.ERROR)
                    .build();

            ServerResponse<String> serverResponse = ServerResponse.Builder
                    .aServerResponse(String.class)
                    .message(serverMessage)
                    .build();

            return new ResponseEntity<>(serverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ServerResponse<String>> returnPersonNotFoundOnDelete(String id) {
        String logMessage = messageRetriever.getMessage(ErrorMessage.PERSON_NOT_FOUND_WHILE_DELETING, id);
        LOGGER.warn(logMessage);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
