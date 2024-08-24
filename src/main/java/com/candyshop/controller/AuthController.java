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

import jakarta.validation.Valid;

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
	public ResponseEntity<AuthResponse>createUserHandler(@Valid @RequestBody User user) throws UserException{

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
		
		AuthResponse authResponse = new AuthResponse(token, true);
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
			
			}
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest ){
		
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		System.out.println(username +" ----- "+password);
		
		Authentication authentication= authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		AuthResponse authResponse= new AuthResponse();
		
		authResponse.setStatus(true);
		authResponse.setJwt(token);
		
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
		
		
	}
	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customeUserService.loadUserByUsername(username);
		
		 System.out.println("sign in userDetails - "+userDetails);
		
		if(userDetails==null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Username invalido.. ");
		}
		
		  if (!passwordEncoder.matches(password, userDetails.getPassword())) {
	        	System.out.println("sign in userDetails - password not match " + userDetails);
	            throw new BadCredentialsException("Invalid username or password");
	        }
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
}

}
