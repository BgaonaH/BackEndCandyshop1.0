package com.candyshop.service;

import org.springframework.stereotype.Service;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Cart;
import com.candyshop.model.User;
import com.candyshop.repository.CartRepository;
import com.candyshop.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	
	
	@Override
	public Cart createCart(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart findUserCart(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
