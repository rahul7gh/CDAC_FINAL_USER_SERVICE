package com.customermanagement.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.customermanagement.dao.IUserDao;
import com.customermanagement.pojo.User;

@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	IUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try
		{
			User userEnt=userDao.findByEmail(username);
			
			System.err.println(userEnt);
			
//			return org.springframework.security.core.userdetails.User.withUsername(userEnt.getEmail())
//					.authorities(new SimpleGrantedAuthority(userEnt.getRole().name())).build();
			
			return new org.springframework.security.core.userdetails.User(userEnt.getEmail(), userEnt.getPassword(),
					Collections.singleton(new SimpleGrantedAuthority(userEnt.getRole().name())));
		}
		catch (Exception e)
		{
			throw new  UsernameNotFoundException("Error Fetching User Data",e);
		}
		
	}
}
