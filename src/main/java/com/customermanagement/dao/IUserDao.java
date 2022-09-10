package com.customermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customermanagement.pojo.User;

public interface IUserDao extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
