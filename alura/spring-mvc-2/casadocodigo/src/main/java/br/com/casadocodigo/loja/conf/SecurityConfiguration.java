package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.daos.UsuarioDAO;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * Observação: Quando o Spring busca a role do banco de dados, ele já
		 * espera que ela tenha o prefixo "ROLE_" no seu nome. Por isso que a
		 * role "ADMIN" no banco está escrita como "ROLE_ADMIN".
		 */
		http.authorizeRequests()
		.antMatchers("/produtos/form").hasRole("ADMIN")
		.antMatchers("/carrinho/**").permitAll()
		.antMatchers("/pagamento/**").permitAll()
		.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
		.antMatchers("/produtos/**").permitAll()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/url-magica-maluca-vldofw309ifgreiojr09gu0i9wejpfj0wijf00e").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao).passwordEncoder(new BCryptPasswordEncoder());
	}

}
