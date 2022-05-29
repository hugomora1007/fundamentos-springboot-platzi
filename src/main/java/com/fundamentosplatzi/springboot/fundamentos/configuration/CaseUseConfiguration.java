package com.fundamentosplatzi.springboot.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUserImplement;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;

@Configuration
public class CaseUseConfiguration {
	
	@Bean
	GetUser getUser(UserService userService) {
		return new GetUserImplement(userService);
	}

}
