package com.candyshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.candyshop.config.JwtTokenProvider;
import com.candyshop.exception.UserException;
import com.candyshop.modal.User;
import com.candyshop.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService{
	
	private UserRepository userRepository;
	private JwtTokenProvider jwtTokenProvider;
	
	public UserServiceImplementation(UserRepository userRepository,JwtTokenProvider jwtprovider) {
		
		this.userRepository= userRepository;
		this.jwtTokenProvider= jwtprovider;
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
		String email= jwtTokenProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not found with email"+ email);
		}
		return user;
	}
//	@Override
//	public List<User> findAllCustomers() {
//		return null;
//	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAllByOrderByCreatAtDesc();
	}
}
