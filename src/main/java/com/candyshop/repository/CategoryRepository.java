package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candyshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("Select c from Category c Where c.name=:name And c.parentCategory.name=:parentCategoryName")
	public Category findByName(String name);
	public Category findByNameAndParant(@Param("name") String name,
			@Param("parantCategoryName")String parentCategoryName);
}
