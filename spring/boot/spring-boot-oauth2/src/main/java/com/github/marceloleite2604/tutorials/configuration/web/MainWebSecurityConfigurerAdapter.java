package com.github.marceloleite2604.tutorials.configuration.web;

import com.github.marceloleite2604.tutorials.configuration.web.oauth2.MainCrsfCustomizer;
import com.github.marceloleite2604.tutorials.configuration.web.oauth2.MainOAuth2LoginCustomizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class MainWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final MainUrlAuthorizationCustomizer mainUrlAuthorizationCustomizer;

    private final MainExceptionHandlingCustomizer mainExceptionHandlingCustomizer;

    private final MainOAuth2LoginCustomizer mainOAuth2LoginCustomizer;

    private final MainLogoutCustomizer mainLogoutCustomizer;

    private final MainCrsfCustomizer mainCrsfCustomizer;

    @Inject
    public MainWebSecurityConfigurerAdapter(MainUrlAuthorizationCustomizer mainUrlAuthorizationCustomizer, MainExceptionHandlingCustomizer mainExceptionHandlingCustomizer, MainOAuth2LoginCustomizer mainOAuth2LoginCustomizer, MainLogoutCustomizer mainLogoutCustomizer, MainCrsfCustomizer mainCrsfCustomizer) {
        this.mainUrlAuthorizationCustomizer = mainUrlAuthorizationCustomizer;
        this.mainExceptionHandlingCustomizer = mainExceptionHandlingCustomizer;
        this.mainOAuth2LoginCustomizer = mainOAuth2LoginCustomizer;
        this.mainLogoutCustomizer = mainLogoutCustomizer;
        this.mainCrsfCustomizer = mainCrsfCustomizer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(mainUrlAuthorizationCustomizer)
                .exceptionHandling(mainExceptionHandlingCustomizer)
                .oauth2Login(mainOAuth2LoginCustomizer)
                .csrf(mainCrsfCustomizer)
                .logout(mainLogoutCustomizer);
    }
}
