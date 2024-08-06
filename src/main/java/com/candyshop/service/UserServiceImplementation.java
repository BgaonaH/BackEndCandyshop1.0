package com.candyshop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.candyshop.config.JwtProvider;
import com.candyshop.exception.UserException;
import com.candyshop.model.User;
import com.candyshop.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService{
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository,JwtProvider jwtprovider) {
		
		this.userRepository= userRepository;
		this.jwtProvider= jwtprovider;
	}
	
	
	@Override
	public User findUserById(long userid) throws UserException {
		Optional<User>user=userRepository.findById(userid);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("user not foind with id :"+userid);
		
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email= jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not found with email"+ email);
		}
		return user;
	}

}
