package com.candyshop.service;

import java.util.List;

import com.candyshop.exception.UserException;
import com.candyshop.modal.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public List<User> findAllUsers();

}
