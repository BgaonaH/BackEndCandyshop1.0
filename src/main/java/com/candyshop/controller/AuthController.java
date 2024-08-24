package com.candyshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candyshop.config.JwtTokenProvider;
import com.candyshop.exception.UserException;
import com.candyshop.modal.User;
import com.candyshop.repository.UserRepository;
import com.candyshop.request.LoginRequest;
import com.candyshop.response.AuthResponse;
import com.candyshop.service.CustomeUserServiceImplementation;

@RestController
@RequestMapping("/auth")

public class AuthController {
	private UserRepository userRepository;
	private JwtTokenProvider jwtTokenProvider;
	private PasswordEncoder passwordEncoder;
	private CustomeUserServiceImplementation customeUserService;
	
	
	public AuthController(UserRepository userRepository, CustomeUserServiceImplementation customeUserService,
			PasswordEncoder passwordEncoder,
			JwtTokenProvider jwtTokenProvider) {
		this.userRepository=userRepository;
		this.customeUserService=customeUserService;
		this.passwordEncoder=passwordEncoder;
		this.jwtTokenProvider=jwtTokenProvider;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException{

		String email= user.getEmail();
		String password= user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		User  isEmailExist =userRepository.findByEmail(email);
		if(isEmailExist!=null) {
			throw new UserException("El correo ya esta vinculado con otra cuenta");
		}
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		
		User savedUser=userRepository.save(createdUser);
		Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token =jwtTokenProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Registro Exitoso");
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
			
			}
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest ){
		
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication= authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		return null;
		
	}
	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customeUserService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Username invalido.. ");
		}
		
		if(passwordEncoder.matches(password, userDetails.getPassword())){
			throw new BadCredentialsException("password invalido.. ");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
}

}
