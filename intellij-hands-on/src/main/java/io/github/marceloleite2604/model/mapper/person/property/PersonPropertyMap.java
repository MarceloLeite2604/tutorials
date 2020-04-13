package io.github.marceloleite2604.model.mapper.person.property;

import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.mapper.AbstractModelPropertyMap;
import io.github.marceloleite2604.model.po.Person;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PersonPropertyMap extends AbstractModelPropertyMap<Person, PersonDto> {

    @Inject
    public PersonPropertyMap(PersonToPersonDtoPropertyMap personToPersonDtoMapper, PersonDtoToPersonPropertyMap personDtoToPersonMapper) {
        super(personToPersonDtoMapper, personDtoToPersonMapper);
    }
}
