package com.candyshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candyshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
	@Query("SELECT p FROM Product p" +
	"WHERE (p.category.name = :category OR :category='')"
			"And ((:minPrice IS NULL AND :maxPrice IS NULL)))")
	public List<Product>filterProducts(@Param("category") String category,
			@Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice,
			@Param("minDiscount") Integer minDiscount,
			@Param("sort") String Sort);
}
