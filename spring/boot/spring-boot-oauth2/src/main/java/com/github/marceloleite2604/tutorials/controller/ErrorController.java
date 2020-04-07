package com.github.marceloleite2604.tutorials.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {

    public static final String SESSION_ATTRIBUTE_ERROR_MESSAGE = "error.message";

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute(SESSION_ATTRIBUTE_ERROR_MESSAGE);
        request.getSession().removeAttribute(SESSION_ATTRIBUTE_ERROR_MESSAGE);
        return message;
    }
}
