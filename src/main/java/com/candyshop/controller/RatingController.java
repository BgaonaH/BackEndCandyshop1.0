package com.candyshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candyshop.exception.ProductException;
import com.candyshop.exception.UserException;
import com.candyshop.modal.Rating;
import com.candyshop.modal.Review;
import com.candyshop.modal.User;
import com.candyshop.request.RatingRequest;
import com.candyshop.service.RatingServices;
import com.candyshop.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	
	private UserService userService;
	private RatingServices ratingServices;
	
	public RatingController(UserService userService,RatingServices ratingServices) {
		this.ratingServices=ratingServices;
		this.userService=userService;
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req,@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user=userService.findUserProfileByJwt(jwt);
		Rating rating=ratingServices.createRating(req, user);
		return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductsReviewHandler(@PathVariable Long productId){
	
		List<Rating> ratings=ratingServices.getProductsRating(productId);
		return new ResponseEntity<>(ratings,HttpStatus.OK);
	}
	//El metodo de abajo nos da una lista de los ratings
	/*@GetMapping("/product/{productId}")
	public ResponseEntity<List<Map<String, Object>>> getProductsReviewHandler(@PathVariable Long productId) {
	    // Recuperar las calificaciones del producto
	    List<Rating> ratings = ratingServices.getProductsRating(productId);

	    // Transformar la lista de Rating a una lista de mapas con los campos deseados
	    List<Map<String, Object>> response = ratings.stream()
	            .map(rating -> {
	                Map<String, Object> map = new HashMap<>();
	                map.put("productId", rating.getProduct().getId());
	                map.put("rating", rating.getRating());
	                return map;
	            })
	            .collect(Collectors.toList());

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}*/
}
