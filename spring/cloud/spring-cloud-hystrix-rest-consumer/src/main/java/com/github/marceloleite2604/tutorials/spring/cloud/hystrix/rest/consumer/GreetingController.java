package com.github.marceloleite2604.tutorials.spring.cloud.hystrix.rest.consumer;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingController {
  
    @Inject
    private GreetingService greetingService;
  
    @GetMapping("/get-greeting/{username}")
    public String getGreeting(Model model, @PathVariable("username") String username) {
        model.addAttribute("greeting", greetingService.getGreeting(username));
        return "greeting-view";
    }
}