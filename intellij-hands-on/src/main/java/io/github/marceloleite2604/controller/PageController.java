package io.github.marceloleite2604.controller;

import io.github.marceloleite2604.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;

@Controller
public class PageController {

    private final PageService pageService;

    @Inject
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping(SitePaths.INDEX)
    public String getIndex(Model model) {
        return pageService.getIndex(model);
    }
}
