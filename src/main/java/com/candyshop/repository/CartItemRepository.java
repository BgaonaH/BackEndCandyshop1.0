package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candyshop.model.Cart;
import com.candyshop.model.CartItem;
import com.candyshop.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT ci from CartItem ci Where ci.cart=:cart And ci.product=:product And ci.userId=:userId")
	public CartItem isCartItemExist(@Param("cart") Cart cart,@Param("product")Product product,@Param("userId")Long userId);
	
}
