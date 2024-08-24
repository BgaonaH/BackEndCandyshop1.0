package com.candyshop.service;

import java.util.List;

import com.candyshop.exception.UserException;
import com.candyshop.modal.User;

public interface UserService {

	public User findUserById(long userid) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

	public List<User> findAllUsers();

}
