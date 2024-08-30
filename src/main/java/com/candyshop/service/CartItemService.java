package com.candyshop.service;

import com.candyshop.exception.CartItemException;
import com.candyshop.exception.UserException;
import com.candyshop.modal.Cart;
import com.candyshop.modal.CartItem;
import com.candyshop.modal.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product, String peso, Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
