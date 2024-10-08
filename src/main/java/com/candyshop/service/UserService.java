package com.candyshop.service;

import com.candyshop.exception.UserException;
import com.candyshop.model.User;

public interface UserService {

	public User findUserById(long userid) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
