package com.customermanagement.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.customermanagement.security.filter.JWTFilter;

@EnableWebMvc
@Configuration
public class SecurityConfig {

	@Autowired
	JWTFilter filter;

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	PasswordEncoder encoder;

	@Bean
	public SecurityFilterChain getChain(HttpSecurity http) throws Exception {
		System.err.println("Configuration toh hai!");
		/*
		 * http .authorizeRequests().anyRequest().authenticated() .and()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 * .and() .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class)
		 * .httpBasic();
		 */

		http.csrf().disable().authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/users").permitAll()
		.antMatchers("/validate-token").permitAll()
		.anyRequest().authenticated().and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	
}
