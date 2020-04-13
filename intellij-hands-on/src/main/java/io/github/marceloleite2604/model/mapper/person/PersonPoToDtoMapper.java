package io.github.marceloleite2604.model.mapper.person;

import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.mapper.PoToDtoMapper;
import io.github.marceloleite2604.model.po.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PersonPoToDtoMapper extends PoToDtoMapper<Person, PersonDto> {

    @Inject
    public PersonPoToDtoMapper(ModelMapper modelMapper) {
        super(Person.class, PersonDto.class, modelMapper);
    }
}
