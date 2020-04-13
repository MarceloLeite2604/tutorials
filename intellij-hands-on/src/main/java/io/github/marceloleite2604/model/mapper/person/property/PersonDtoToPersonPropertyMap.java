package io.github.marceloleite2604.model.mapper.person.property;

import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.mapper.converter.StringToUuidConverter;
import io.github.marceloleite2604.model.po.Person;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PersonDtoToPersonPropertyMap extends PropertyMap<PersonDto, Person> {

    private final StringToUuidConverter stringToUuidConverter;

    @Inject
    public PersonDtoToPersonPropertyMap(StringToUuidConverter stringToUuidConverter) {
        this.stringToUuidConverter = stringToUuidConverter;
    }

    @Override
    protected void configure() {
        using(stringToUuidConverter).map(source.getId(), destination.getId());
    }
}
