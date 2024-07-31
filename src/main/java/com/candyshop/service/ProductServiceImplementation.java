package com.candyshop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Category;
import com.candyshop.model.Product;
import com.candyshop.repository.CategoryRepository;
import com.candyshop.repository.ProductRepository;
import com.candyshop.request.CreateProductRequest;

@Service
public class ProductServiceImplementation  implements ProductService {

private ProductRepository productRepository;
private UserService userService;
private CategoryRepository categoryRepository;

 	public ProductServiceImplementation(ProductRepository productRepository, 
 			UserService userService, 
 			CategoryRepository categoryRepository) {
 		this.productRepository=productRepository;
 		this.userService=userService;
 		this.categoryRepository=categoryRepository;
 	}
 	

	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Category topLevel = categoryRepository.findByName(req.getTopLavelCategory());
		
		if(topLevel==null) {
			Category topLavelCategory=new Category();
			topLavelCategory.setName(req.getTopLavelCategory());
			topLavelCategory.setLevel(1);
			
			topLevel=categoryRepository.save(topLavelCategory);
		}
		
		Category secondLevel = categoryRepository.findByNameAndParant(req.getSecondLavelCategory(),topLevel.getName());
		
		if(secondLevel==null) {
			Category secondLavelCategory=new Category();
			secondLavelCategory.setName(req.getSecondLavelCategory());
			secondLavelCategory.setParentCategory(topLevel);
			secondLavelCategory.setLevel(2);
			
			secondLevel=categoryRepository.save(secondLavelCategory);
		}
		
		
		Category thirdLevel = categoryRepository.findByNameAndParant(req.getThirdLavelCategory(),secondLevel.getName());
		
		if(thirdLevel==null) {
			Category thirdLavelCategory=new Category();
			thirdLavelCategory.setName(req.getThirdLavelCategory());
			thirdLavelCategory.setParentCategory(secondLevel);
			thirdLavelCategory.setLevel(3);
			
			thirdLevel=categoryRepository.save(thirdLavelCategory);
		}
		
		Product product = new Product ();
		product.setTitle(req.getTitle());
		product.setDescription(req.getDescription());
		product.setDiscountPrice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscointPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		
		Product savedProduct = productRepository.save(product);
		
		return savedProduct;
		
	
		
		
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product=findProductById(productId);
		
		productRepository.delete(product);
		return "Product deleted Succesfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		
		Product product = findProductById(productId);
		
		if(req.getQuantity()!=0) {
			product.setQuantity(req.getQuantity());
		}
		
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long Id) throws ProductException {
 		Optional<Product> opt=productRepository.findById(Id);
 		
 		if (opt.isPresent()) {
		return opt.get();
 		}
	throw new ProductException("Product not found with id -"+Id) ;
	}
	
	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, Integer minPrice, Integer maxPrice, Integer maxDiscount,
			Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		
		if(stock!=null) {
			if(stock.equals("in_stock")){
				products = products.stream().filter(p -> p.getQuantity()>0).collect(Collectors.toList());
			}
		}
	return null;

	}
	}
		
	




