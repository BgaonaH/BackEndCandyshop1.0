package com.candyshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
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
		
		return null;
		
		
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Long productId, Product product) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, Integer minPrice, Integer maxPrice, Integer maxDiscount,
			Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
