package io.github.marceloleite2604.dao;

import io.github.marceloleite2604.dao.repository.PersonRepository;
import io.github.marceloleite2604.exception.ApplicationRuntimeException;
import io.github.marceloleite2604.model.message.ErrorMessage;
import io.github.marceloleite2604.model.po.Person;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class PersonDao {

    private PersonRepository personRepository;

    @Inject
    public PersonDao(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(UUID id) {

        if (Objects.isNull(id)) {
            throw new ApplicationRuntimeException(ErrorMessage.ID_IS_MANDATORY);
        }

        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isEmpty()) {
            throw new ApplicationRuntimeException(ErrorMessage.RECORD_NOT_FOUND, Person.class, id);
        }

        return optionalPerson.get();
    }

    public Optional<Person> findOptionalById(UUID id) {
        return personRepository.findById(id);
    }

    public void deleteById(UUID id) {
        personRepository.deleteById(id);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
