package com.github.marceloleite2604.tutorials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class MyAccessTokenController {

  @Autowired
  private OAuth2AuthorizedClientService clientService;

  @RequestMapping("/my-access-token")
  String home(Principal user) {
    OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) user;
    String authorizedClientRegistrationId = token.getAuthorizedClientRegistrationId();
    String name = user.getName();
    OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(authorizedClientRegistrationId, name);
    return "token: " + client.getAccessToken().getTokenValue();
  }

}