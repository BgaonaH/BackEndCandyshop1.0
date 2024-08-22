package com.candyshop.service;

import com.candyshop.exception.CartItemException;
import com.candyshop.exception.UserException;
import com.candyshop.modal.Cart;
import com.candyshop.modal.CartItem;
import com.candyshop.modal.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem carItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, Long userid);
	
	public void removeCartItem(Long userid, Long caritemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long caritemid) throws CartItemException;


	
}
