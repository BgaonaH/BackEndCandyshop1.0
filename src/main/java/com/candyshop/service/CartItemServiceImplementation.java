package com.candyshop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.candyshop.exception.CartItemException;
import com.candyshop.exception.UserException;
import com.candyshop.model.Cart;
import com.candyshop.model.CartItem;
import com.candyshop.model.Product;
import com.candyshop.model.User;
import com.candyshop.repository.CartItemRepository;
import com.candyshop.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {

	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;
	
	public CartItemServiceImplementation(CartRepository cartRepository,UserService userService,
			CartItemRepository cartItemRepository ) {
		this.cartItemRepository=cartItemRepository;
		this.userService=userService;
		this.cartRepository=cartRepository;
		
	}
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountPrice(cartItem.getProduct().getDiscountPrice()*cartItem.getQuantity());
		
		CartItem createdCartItem=cartItemRepository.save(cartItem);
		
		return createdCartItem;
		
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(item.getUserId());
		
		if(user.getId().equals(userId)){
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountPrice(item.getProduct().getDiscountPrice()*item.getQuantity());
		}
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, Long userId) {
		
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, userId);
		
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		
		User user = userService.findUserById(cartItem.getUserId());
		
		User reqUser=userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())){
			cartItemRepository.deleteById(cartItemId);
		}else {
			throw new UserException("You can't remove another users item"); 
		}
	}

	@Override
	public CartItem findCartItemById(Long caritemid) throws CartItemException {
		Optional<CartItem>opt= cartItemRepository.findById(caritemid);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("Cart item not found");
	}

}
