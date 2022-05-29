package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import java.util.List;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

public class GetUserImplement implements GetUser {
	
	private UserService userService;
	
	public GetUserImplement(UserService userService) {
		this.userService = userService;
	}

	@Override
	public List<User> getAll() {
		return this.userService.getAllUsers();
	}

}
