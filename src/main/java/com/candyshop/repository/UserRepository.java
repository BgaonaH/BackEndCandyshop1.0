package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyshop.modal.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
	
}
