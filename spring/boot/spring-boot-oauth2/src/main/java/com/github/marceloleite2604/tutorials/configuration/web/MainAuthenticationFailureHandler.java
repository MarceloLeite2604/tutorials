package com.github.marceloleite2604.tutorials.configuration.web;

import com.github.marceloleite2604.tutorials.controller.ErrorController;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MainAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String exceptionMessage = exception.getMessage();
        request.getSession().setAttribute(ErrorController.SESSION_ATTRIBUTE_ERROR_MESSAGE, exceptionMessage);
    }
}
