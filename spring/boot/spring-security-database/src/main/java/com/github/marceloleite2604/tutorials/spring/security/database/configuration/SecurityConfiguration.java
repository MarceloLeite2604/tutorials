package com.github.marceloleite2604.tutorials.spring.security.database.configuration;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Inject
	public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder,
			DataSource dataSource) throws Exception {

		authenticationManagerBuilder.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select usuario, senha, habilitado from usuarios where usuario=?")
				.authoritiesByUsernameQuery(
						"select usuario, papel from papeis_usuarios where usuario=?")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests()
				.antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')")
				.and()
				.formLogin()
				// .loginPage("/login")
				// .failureUrl("/login?error")
				.usernameParameter("usuario")
				.passwordParameter("senha")
				.and()
				.logout()
				// .logoutSuccessUrl("/login?logout")
				.and()
				.exceptionHandling()
				// .accessDeniedPage("/403")
				.and()
				.csrf();
	}
}
