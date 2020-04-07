package com.github.marceloleite2604.tutorials.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    private static final String OAUTH2_ATTRIBUTE_NAME = "name";

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return Collections.singletonMap("name", oAuth2User.getAttribute(OAUTH2_ATTRIBUTE_NAME));
    }
}
