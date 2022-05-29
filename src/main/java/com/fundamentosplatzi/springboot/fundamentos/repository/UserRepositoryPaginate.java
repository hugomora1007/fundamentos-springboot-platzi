package com.fundamentosplatzi.springboot.fundamentos.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;

@Repository
public interface UserRepositoryPaginate extends PagingAndSortingRepository<User, Long> {
	
	List<User> findAll();

}
