package com.candyshop.service;

import com.candyshop.exception.UserException;
import com.candyshop.modal.User;

public interface UserService {

	public User findUserById(long userid) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
}
