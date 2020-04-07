package com.github.marceloleite2604.tutorials.configuration.web;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.stereotype.Component;

@Component
public class MainLogoutCustomizer implements Customizer<LogoutConfigurer<HttpSecurity>> {

    public static final String LOGOUT_SUCCESS_URL = "/";

    @Override
    public void customize(LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer) {
        httpSecurityLogoutConfigurer.logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .permitAll();
    }
}
