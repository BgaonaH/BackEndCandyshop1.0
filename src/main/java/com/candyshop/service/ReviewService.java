package com.candyshop.service;

import java.util.List;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Review;
import com.candyshop.model.User;
import com.candyshop.request.ReviewRequest;

public interface ReviewService {

	public Review createReive(ReviewRequest req, User user) throws ProductException;
	public List<Review>getAllReview(Long productId);
}
