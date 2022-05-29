package com.fundamentosplatzi.springboot.fundamentos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;

@Service
public class UserService {

	private static final Log LOGGER = LogFactory.getLog(UserService.class);

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveTransactional(List<User> users) {
		users.stream().peek(user -> LOGGER.info("Usuario insertado: " + user)).forEach(this.userRepository::save);
		// .forEach(user -> this.userRepository.save(user));
	}

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	public User save(User newUser) {
		return this.userRepository.save(newUser);
	}

	public void delete(Long id) {
		this.userRepository.delete(new User(id));
	}

	public User update(User user, Long id) {
		return this.userRepository.findById(id).map(userActualiza -> {
			userActualiza.setEmail(user.getEmail());
			userActualiza.setBirthDate(user.getBirthDate());
			userActualiza.setName(user.getName());
			return this.userRepository.save(userActualiza);
		}).orElse(null);
	}

}
