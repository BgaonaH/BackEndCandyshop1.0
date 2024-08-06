package com.candyshop.service;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Cart;
import com.candyshop.model.User;
import com.candyshop.request.AddItemRequest;

public interface CartService {

	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
