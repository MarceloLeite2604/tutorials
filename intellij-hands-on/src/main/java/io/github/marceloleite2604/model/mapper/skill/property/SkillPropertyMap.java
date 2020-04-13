package io.github.marceloleite2604.model.mapper.skill.property;

import io.github.marceloleite2604.model.dto.SkillDto;
import io.github.marceloleite2604.model.mapper.AbstractModelPropertyMap;
import io.github.marceloleite2604.model.po.Skill;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class SkillPropertyMap extends AbstractModelPropertyMap<Skill, SkillDto> {

    @Inject
    public SkillPropertyMap(PropertyMap<Skill, SkillDto> poToDtoPropertyMap, PropertyMap<SkillDto, Skill> dtoToPoPropertyMap) {
        super(poToDtoPropertyMap, dtoToPoPropertyMap);
    }
}
