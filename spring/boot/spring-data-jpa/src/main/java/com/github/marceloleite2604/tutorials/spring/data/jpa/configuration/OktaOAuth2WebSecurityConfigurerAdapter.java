package com.github.marceloleite2604.tutorials.spring.data.jpa.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.regexMatchers("/api/.+")
				.authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
	}

}
