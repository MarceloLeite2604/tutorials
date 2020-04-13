package io.github.marceloleite2604.bo;

import io.github.marceloleite2604.dao.SkillDao;
import io.github.marceloleite2604.model.mapper.skill.SkillPoToDtoMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class SkillBo {

    private final SkillDao skillDao;

    private final SkillPoToDtoMapper skillPoToDtoMapper;

    @Inject
    public SkillBo(SkillDao skillDao, SkillPoToDtoMapper skillPoToDtoMapper) {
        this.skillDao = skillDao;
        this.skillPoToDtoMapper = skillPoToDtoMapper;
    }

    public SkillPoToDtoMapper getSkillPoToDtoMapper() {
        return skillPoToDtoMapper;
    }
}
