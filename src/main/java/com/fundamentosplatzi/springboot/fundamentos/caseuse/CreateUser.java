package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import org.springframework.stereotype.Component;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@Component
public class CreateUser {

	private UserService userService;

	public CreateUser(UserService userService) {
		this.userService = userService;
	}

	public User save(User newUser) {
		return this.userService.save(newUser);
	}
	
}
