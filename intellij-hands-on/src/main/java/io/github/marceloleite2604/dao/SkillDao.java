package io.github.marceloleite2604.dao;

import io.github.marceloleite2604.dao.repository.SkillRepository;
import io.github.marceloleite2604.model.po.Skill;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class SkillDao {

    private SkillRepository skillRepository;

    @Inject
    public SkillDao(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }
}
