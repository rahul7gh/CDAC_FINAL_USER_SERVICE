package com.customermanagement.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class BeanProvider2 {

	@Autowired
	PasswordEncoder encoder;
	@Bean
	public InMemoryUserDetailsManager InMemUserDetailsMgr() {

		UserDetails u1 = User.withUsername("rahul").password(encoder.encode("rahul"))
				.authorities(new SimpleGrantedAuthority("ROLE_USER")).build();
		return new InMemoryUserDetailsManager(u1);
	}
}
