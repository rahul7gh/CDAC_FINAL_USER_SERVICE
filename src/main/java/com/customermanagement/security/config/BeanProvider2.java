package com.customermanagement.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.customermanagement.service.CustomUserDetailsService;

@Configuration
public class BeanProvider2 {

	@Autowired
	PasswordEncoder encoder;
	
//	@Bean
//	public InMemoryUserDetailsManager InMemUserDetailsMgr() {
//
////		UserDetails u1 = User.withUsername("rahul").password(encoder.encode("rahul"))
//		UserDetails u1 = User.withUsername("rahul").password("rahul")
//				.authorities(new SimpleGrantedAuthority("ROLE_USER")).build();
//		return new InMemoryUserDetailsManager(u1);
//	}
	
	@Bean
	public UserDetailsService customUserDeailsService() {

//		UserDetails u1 = User.withUsername("rahul").password(encoder.encode("rahul"))
//				.authorities(new SimpleGrantedAuthority("ROLE_USER")).build();
		
		return new CustomUserDetailsService();
	}
}
