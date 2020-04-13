package io.github.marceloleite2604.model.mapper.person.property;

import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.mapper.converter.UuidToStringConverter;
import io.github.marceloleite2604.model.po.Person;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PersonToPersonDtoPropertyMap extends PropertyMap<Person, PersonDto> {

    private final UuidToStringConverter uuidToStringConverter;

    @Inject
    public PersonToPersonDtoPropertyMap(UuidToStringConverter uuidToStringConverter) {
        this.uuidToStringConverter = uuidToStringConverter;
    }

    @Override
    protected void configure() {
        using(uuidToStringConverter).map(source.getId(), destination.getId());
    }
}
