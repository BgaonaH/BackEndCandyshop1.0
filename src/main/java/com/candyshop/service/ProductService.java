package com.candyshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Product;
import com.candyshop.request.CreateProductRequest;

public interface ProductService {
	
	
	
	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId, Product req) throws ProductException;
	
	public Product findProductById(Long id) throws ProductException;
	
	public List<Product>findProductByCategory(String category);
	
	public List<Product> searchProduct(String query);
	
	public Page<Product>getAllProducts(String category, Integer minPrice, Integer maxPrice,
			Integer maxDiscount, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	
	public List<Product> recentlyAddedProduct();

	public List<Product> getAllProducts();
}
