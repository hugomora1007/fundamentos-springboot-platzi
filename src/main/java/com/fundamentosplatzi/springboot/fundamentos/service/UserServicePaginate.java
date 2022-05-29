package com.fundamentosplatzi.springboot.fundamentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepositoryPaginate;

@Service
public class UserServicePaginate {

	private UserRepositoryPaginate userRepositoryPaginate;

	public UserServicePaginate(UserRepositoryPaginate userRepository) {
		super();
		this.userRepositoryPaginate = userRepository;
	}

	public List<User> findAll() {
		return this.userRepositoryPaginate.findAll();
	}

}
