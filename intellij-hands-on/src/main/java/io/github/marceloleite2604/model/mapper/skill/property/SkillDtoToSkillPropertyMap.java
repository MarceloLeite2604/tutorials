package io.github.marceloleite2604.model.mapper.skill.property;

import io.github.marceloleite2604.model.dto.SkillDto;
import io.github.marceloleite2604.model.mapper.converter.StringToUuidConverter;
import io.github.marceloleite2604.model.po.Skill;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class SkillDtoToSkillPropertyMap extends PropertyMap<SkillDto, Skill> {

    private StringToUuidConverter stringToUuidConverter;

    @Inject
    public SkillDtoToSkillPropertyMap(StringToUuidConverter stringToUuidConverter) {
        this.stringToUuidConverter = stringToUuidConverter;
    }

    @Override
    protected void configure() {
        using(stringToUuidConverter).map(source.getId(), destination.getId());
    }
}
