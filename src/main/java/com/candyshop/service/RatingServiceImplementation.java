package com.candyshop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.candyshop.exception.ProductException;
import com.candyshop.modal.Product;
import com.candyshop.modal.Rating;
import com.candyshop.modal.User;
import com.candyshop.repository.ProductRepository;
import com.candyshop.repository.RatingRepository;
import com.candyshop.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingServices{
	
	private RatingRepository ratingRepository;
	private ProductService productService;
	
	public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService) {
		this.ratingRepository=ratingRepository;
		this.productService=productService;
	}

	@Override
	public Rating createRating(RatingRequest req,User user) throws ProductException {
		
		Product product=productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		product.setNumRatings(product.getNumRatings() + 1);
		productService.updateProduct(product.getId(), product);

		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllProductsRating(productId);
	}
	
	

}
