package com.miranda.springboot.springbootquestionary.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");
	}
	
	//Authorization : Roles --> Access
	
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests()
			.antMatchers("/surveys/**").hasRole("USER")
			.antMatchers("/users/**").hasRole("USER")
			.antMatchers("/**").hasRole("ADMIN")
			.and().csrf().disable()
			.headers().frameOptions().disable();
	}
	
}