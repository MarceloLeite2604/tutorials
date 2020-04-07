package com.github.marceloleite2604.tutorials.configuration.web.oauth2;

import com.github.marceloleite2604.tutorials.configuration.web.MainAuthenticationFailureHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class MainOAuth2LoginCustomizer implements Customizer<OAuth2LoginConfigurer<HttpSecurity>> {

    private final MainAuthenticationFailureHandler mainAuthenticationFailureHandler;

    @Inject
    public MainOAuth2LoginCustomizer(MainAuthenticationFailureHandler mainAuthenticationFailureHandler) {
        this.mainAuthenticationFailureHandler = mainAuthenticationFailureHandler;
    }

    @Override
    public void customize(OAuth2LoginConfigurer<HttpSecurity> httpSecurityOAuth2LoginConfigurer) {
        httpSecurityOAuth2LoginConfigurer.failureHandler(mainAuthenticationFailureHandler);
    }
}
