package com.candyshop.service;

import java.util.List;

import com.candyshop.exception.ProductException;
import com.candyshop.modal.Rating;
import com.candyshop.modal.User;
import com.candyshop.request.RatingRequest;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
