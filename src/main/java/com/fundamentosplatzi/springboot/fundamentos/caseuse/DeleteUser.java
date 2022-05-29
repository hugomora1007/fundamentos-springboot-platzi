package com.fundamentosplatzi.springboot.fundamentos.caseuse;

import org.springframework.stereotype.Component;

import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@Component
public class DeleteUser {

	private UserService userService;

	public DeleteUser(UserService userService) {
		this.userService = userService;
	}

	public void remove(Long id) {
		this.userService.delete(id);
	}
	
}
