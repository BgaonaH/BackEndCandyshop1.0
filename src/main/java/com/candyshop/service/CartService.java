package com.candyshop.service;

import com.candyshop.exception.ProductException;
import com.candyshop.modal.Cart;
import com.candyshop.modal.CartItem;
import com.candyshop.modal.User;
import com.candyshop.request.AddItemRequest;

public interface CartService {

	
	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
