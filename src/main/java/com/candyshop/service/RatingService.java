package com.candyshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Rating;
import com.candyshop.model.User;
import com.candyshop.request.RatingRequest;


public interface RatingService {
	
	public Rating createRating(RatingRequest req, User user) throws ProductException;
	public List<Rating>getProductsRating(Long productId);

}
