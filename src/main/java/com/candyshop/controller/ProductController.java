package com.candyshop.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Product;
import com.candyshop.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;
	
	@GetMapping ("/products")
	public ResponseEntity<Page<Product>>findProductByCategoryHandler(@RequestParam String category,
			@RequestParam List<String>color, @RequestParam Integer minPrice,
			@RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort,
			@RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
		
	
	Page <Product> res= productService.getAllProduct(category, minPrice, maxPrice, minDiscount, minDiscount, sort, stock, pageNumber, pageSize);
	
	System.out.println("complete products");
	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	
		
	}
	
	
	@GetMapping ("/products/id/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException{
		
		Product product = productService.findProductById(productId);
		
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
		
	}
	
}
