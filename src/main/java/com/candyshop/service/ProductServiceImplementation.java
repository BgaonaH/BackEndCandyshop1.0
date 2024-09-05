package com.candyshop.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.candyshop.exception.ProductException;
import com.candyshop.modal.Category;
import com.candyshop.modal.Product;
import com.candyshop.repository.CategoryRepository;
import com.candyshop.repository.ProductRepository;
import com.candyshop.request.CreateProductRequest;
import com.candyshop.user.domain.ProductSubCategory;

@Service
public class ProductServiceImplementation implements ProductService {
	
	private ProductRepository productRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;
	
	public ProductServiceImplementation(ProductRepository productRepository,UserService userService,CategoryRepository categoryRepository) {
		this.productRepository=productRepository;
		this.userService=userService;
		this.categoryRepository=categoryRepository;
	}
	

	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Category topLevel=categoryRepository.findByName(req.getTopLavelCategory());
		
		if(topLevel==null) {
			
			Category topLavelCategory=new Category();
			topLavelCategory.setName(req.getTopLavelCategory());
			topLavelCategory.setLevel(1);
			
			topLevel= categoryRepository.save(topLavelCategory);
		}
		
		Category secondLevel=categoryRepository.
				findByNameAndParant(req.getSecondLavelCategory(),topLevel.getName());
		if(secondLevel==null) {
			
			Category secondLavelCategory=new Category();
			secondLavelCategory.setName(req.getSecondLavelCategory());
			secondLavelCategory.setParentCategory(topLevel);
			secondLavelCategory.setLevel(2);
			
			secondLevel= categoryRepository.save(secondLavelCategory);
		}

		Category thirdLevel=categoryRepository.findByNameAndParant(req.getThirdLavelCategory(),secondLevel.getName());
		if(thirdLevel==null) {
			
			Category thirdLavelCategory=new Category();
			thirdLavelCategory.setName(req.getThirdLavelCategory());
			thirdLavelCategory.setParentCategory(secondLevel);
			thirdLavelCategory.setLevel(3);
			
			thirdLevel=categoryRepository.save(thirdLavelCategory);
		}
		
		
		Product product=new Product();
		product.setTitle(req.getTitle());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscountPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setQuantity(req.getQuantity());
		product.setWeight(req.getWeight());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		
		Product savedProduct= productRepository.save(product);
		
		System.out.println("products - "+product);
		
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		
		Product product=findProductById(productId);
		
		System.out.println("delete product "+product.getId()+" - "+productId);
//		product.getPeso().clear();
//		productRepository.save(product);
//		product.getCategory().
		productRepository.delete(product);
		
		return "Product deleted Successfully";
	}

	@Override
	public Product updateProduct(Long productId,Product req) throws ProductException {
		Product product=findProductById(productId);
		
		if(req.getQuantity()!=0) {
			product.setQuantity(req.getQuantity());
		}
		if(req.getDescription()!=null) {
			product.setDescription(req.getDescription());
		}
		
		
			
		
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		Optional<Product> opt=productRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("product not found with id "+id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		
		System.out.println("category --- "+category);
		
		List<Product> products = productRepository.findByCategory(category);
		
		return products;
	}

	@Override
	public List<Product> searchProduct(String query) {
		List<Product> products=productRepository.searchProduct(query);
		return products;
	}

	
	@Override
	public Page<Product> getAllProduct(String category, List<Integer> weights, Integer minPrice, Integer maxPrice, 
	        Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		// Determinar el campo de ordenación y la dirección
	    
		Sort.Direction sortDirection = Sort.Direction.ASC; // Valor predeterminado
	    String sortField = "id"; // Valor predeterminado para el campo de ordenación

	    if (sort != null) {
	        if (sort.equalsIgnoreCase("price_high")) {
	            sortDirection = Sort.Direction.ASC;
	            sortField = "price";
	        } else if (sort.equalsIgnoreCase("price_low")) {
	            sortDirection = Sort.Direction.DESC;
	            sortField = "price";
	        } else if (sort.equalsIgnoreCase("name_asc")) {
	            sortDirection = Sort.Direction.ASC;
	            sortField = "name";
	        } else if (sort.equalsIgnoreCase("name_desc")) {
	            sortDirection = Sort.Direction.DESC;
	            sortField = "name";
	        }
	        // Puedes añadir más casos si tienes más criterios de ordenación
	    }

	    Pageable pageable = PageRequest.of(pageNumber, pageSize,  Sort.by(sortDirection, sortField));
	    
	    List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

	    // Filtrar por peso si la lista de pesos no está vacía
	    if (weights != null && !weights.isEmpty()) {
	        products = products.stream()
	                .filter(p -> weights.contains(p.getWeight()))
	                .collect(Collectors.toList());
	    }

	    // Filtrar por stock si se especifica
	    if (stock != null) {
	        if (stock.equals("in_stock")) {
	            products = products.stream()
	                    .filter(p -> p.getQuantity() > 0)
	                    .collect(Collectors.toList());
	        } else if (stock.equals("out_of_stock")) {
	            products = products.stream()
	                    .filter(p -> p.getQuantity() < 1)
	                    .collect(Collectors.toList());
	        }
	    }

	    // Paginar la lista de productos
	    int startIndex = (int) pageable.getOffset();
	    int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

	    List<Product> pageContent = products.subList(startIndex, endIndex);
	    Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
	    return filteredProducts;
	}


	@Override
	public List<Product> recentlyAddedProduct() {
		
		return productRepository.findTop10ByOrderByCreatedAtDesc();
	}

}
