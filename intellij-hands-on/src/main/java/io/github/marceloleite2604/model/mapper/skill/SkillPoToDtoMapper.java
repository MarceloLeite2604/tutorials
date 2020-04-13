package io.github.marceloleite2604.model.mapper.skill;

import io.github.marceloleite2604.model.dto.SkillDto;
import io.github.marceloleite2604.model.mapper.PoToDtoMapper;
import io.github.marceloleite2604.model.po.Skill;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class SkillPoToDtoMapper extends PoToDtoMapper<Skill, SkillDto> {

    @Inject
    public SkillPoToDtoMapper(ModelMapper modelMapper) {
        super(Skill.class, SkillDto.class, modelMapper);
    }
}
