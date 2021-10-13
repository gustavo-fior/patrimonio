package br.com.gx.patrimonio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoUsuarioService autenticacaoService;

	// Metodo sobrescrito para podermos ter uma instancia do authentication
	// manager (Spring nao consegue injetar)
	// @Bean para o Spring saber que o metodo devolve o Authentication Manager
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/signup").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin(
				login -> login.loginPage("/login").defaultSuccessUrl("/users/home", true).permitAll()
				)
		.logout(
				logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/index")
				)
		.csrf().disable();

	}

	// JDBC Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

		auth.userDetailsService(autenticacaoService).passwordEncoder(encoder);

	}

}
