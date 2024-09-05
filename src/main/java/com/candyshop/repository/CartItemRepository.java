package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.candyshop.modal.Cart;
import com.candyshop.modal.CartItem;
import com.candyshop.modal.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	@Query("SELECT ci From CartItem ci Where ci.cart=:cart And ci.product=:product And ci.weight=:weight And ci.userId=:userId")
	public CartItem isCartItemExist(@Param("cart")Cart cart,@Param("product")Product product, @Param("weight")String weight, @Param("userId")Long userId);
	
}
