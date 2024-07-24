package com.candyshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Product;
import com.candyshop.request.CreateProductRequest;

public interface ProductService {
	
	
	
	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId, Product product) throws ProductException;
	
	public Product findProductById(Long id) throws ProductException;
	
	public List<Product>findProductByCategory(String category);
	
	public Page<Product>getAllProduct(String category, Integer minPrice, Integer maxPrice,
			Integer maxDiscount, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	
}
