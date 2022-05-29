package com.fundamentosplatzi.springboot.fundamentos.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepositoryPaginate;
import com.fundamentosplatzi.springboot.fundamentos.service.UserServicePaginate;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private GetUser getUser;
	private CreateUser createUser;
	private DeleteUser deleteUser;
	private UpdateUser updateUser;
	private UserRepositoryPaginate userRepositoryPaginate;
	
	public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser,
			UpdateUser updateUser, UserRepositoryPaginate userRepositoryPaginate) {
		this.getUser = getUser;
		this.createUser = createUser;
		this.deleteUser = deleteUser;
		this.updateUser = updateUser;
		this.userRepositoryPaginate = userRepositoryPaginate;
	}
	
	@GetMapping("/")
	List<User> get() {
		return this.getUser.getAll();
	}
	
	@PostMapping("/")
	ResponseEntity<User> newUser(@RequestBody User newUser) {
		return new ResponseEntity<>(this.createUser.save(newUser), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity deleteUser(@PathVariable Long id) {
		this.deleteUser.remove(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<User> replaceUser(@RequestBody User user, @PathVariable Long id) {
		return new ResponseEntity<User>(this.updateUser.update(user, id), HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
		return this.userRepositoryPaginate.findAll(PageRequest.of(page, size)).getContent();
	}

}
