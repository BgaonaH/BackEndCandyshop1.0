package com.candyshop.service;

import java.util.List;

import com.candyshop.exception.ProductException;
import com.candyshop.modal.Review;
import com.candyshop.modal.User;
import com.candyshop.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}

