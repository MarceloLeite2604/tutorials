package io.github.marceloleite2604.bo;

import io.github.marceloleite2604.dao.PersonDao;
import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.mapper.person.PersonPoToDtoMapper;
import io.github.marceloleite2604.model.po.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Component
public class PersonBo {

    private final PersonDao personDao;

    private final PersonPoToDtoMapper personPoToDtoMapper;

    @Inject
    public PersonBo(@Named("MyCustomPersonDao") PersonDao personDao, PersonPoToDtoMapper personPoToDtoMapper) {
        this.personDao = personDao;
        this.personPoToDtoMapper = personPoToDtoMapper;
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    public PersonPoToDtoMapper getPersonPoToDtoMapper() {
        return personPoToDtoMapper;
    }

    public void sortByName(List<Person> persons) {
        persons.sort(Comparator.comparing(Person::getName));
    }

    public Person findById(String id) {
        UUID uuid = UUID.fromString(id);
        return findById(uuid);
    }

    public Person findById(UUID id) {
        return personDao.findById(id);
    }

    public Optional<Person> findOptionalById(String id) {
        UUID uuid = UUID.fromString(id);
        return findOptionalById(uuid);
    }

    public Optional<Person> findOptionalById(UUID id) {
        if (Objects.isNull(id)) {
            return Optional.empty();
        }

        return personDao.findOptionalById(id);
    }

    public boolean isNew(PersonDto personDto) {
        return StringUtils.isBlank(personDto.getId());
    }

    public PersonDto save(PersonDto personDto) {
        Person person = personPoToDtoMapper.mapToPo(personDto);
        person = save(person);
        return personPoToDtoMapper.mapToDto(person);
    }

    public Person save(Person person) {
        return personDao.save(person);
    }

    public void deleteById(String id) {
        deleteById(UUID.fromString(id));
    }

    private void deleteById(UUID id) {
        personDao.deleteById(id);
    }

    public void delete(Person person) {
        personDao.delete(person);
    }
}
