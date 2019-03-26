package com.github.marceloleite2604.tutorials.spring.cloud.sso;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@EnableOAuth2Sso
public class LoginConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.and()
				.antMatcher("/dashboard/**")
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.csrf()
				.csrfTokenRepository(csrfTokenRepository())
				.and()
				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
				.logout()
				.logoutUrl("/dashboard/logout")
				.permitAll()
				.logoutSuccessUrl("/");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/", "/favicon.ico", "/index.html", "/home.html", "/dashboard.html",
						"/js/**", "/css/**", "/webjars/**");
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {

				Optional<CsrfToken> optionalCsrfToken = Optional
						.ofNullable((CsrfToken) request.getAttribute(CsrfToken.class.getName()));

				if (optionalCsrfToken.isPresent()) {
					Cookie cookie = new Cookie("XSRF-TOKEN", optionalCsrfToken.get()
							.getToken());
					cookie.setPath("/");
					response.addCookie(cookie);
				}

				filterChain.doFilter(request, response);
			}
		};
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
		httpSessionCsrfTokenRepository.setHeaderName("X-XSRF-TOKEN");
		return httpSessionCsrfTokenRepository;
	}
}
