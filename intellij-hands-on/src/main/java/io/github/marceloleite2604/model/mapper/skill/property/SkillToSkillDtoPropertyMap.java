package io.github.marceloleite2604.model.mapper.skill.property;

import io.github.marceloleite2604.model.dto.SkillDto;
import io.github.marceloleite2604.model.mapper.converter.UuidToStringConverter;
import io.github.marceloleite2604.model.po.Skill;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class SkillToSkillDtoPropertyMap extends PropertyMap<Skill, SkillDto> {

    private UuidToStringConverter uuidToStringConverter;

    @Inject
    public SkillToSkillDtoPropertyMap(UuidToStringConverter uuidToStringConverter) {
        this.uuidToStringConverter = uuidToStringConverter;
    }

    @Override
    protected void configure() {
        using(uuidToStringConverter).map(source.getId(), destination.getId());
    }
}
