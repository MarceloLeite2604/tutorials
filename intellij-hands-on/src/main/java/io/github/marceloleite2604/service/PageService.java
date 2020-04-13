package io.github.marceloleite2604.service;

import io.github.marceloleite2604.bo.PersonBo;
import io.github.marceloleite2604.model.dto.PersonDto;
import io.github.marceloleite2604.model.po.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.inject.Inject;
import java.util.List;

@Service
public class PageService {

    private PersonBo personBo;

    @Inject
    public PageService(PersonBo personBo) {
        this.personBo = personBo;
    }

    public String getIndex(Model model) {
        return TemplateLocations.INDEX;
    }
}
