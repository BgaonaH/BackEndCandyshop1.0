package com.candyshop.service;

import org.springframework.stereotype.Service;

import com.candyshop.exception.ProductException;
import com.candyshop.model.Cart;
import com.candyshop.model.CartItem;
import com.candyshop.model.Product;
import com.candyshop.model.User;
import com.candyshop.repository.CartRepository;
import com.candyshop.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService, 
			ProductService productService) {
		this.cartRepository=cartRepository;
		this.cartItemService=cartItemService;
		this.productService=productService;
	}
	
	@Override
	public Cart createCart(User user) {
		
		
		Cart cart= new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		
		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductid());
		
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, userId);
		
		if(isPresent==null) {
			CartItem cartItem = new CartItem ();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price = req.getQuantity()*product.getDiscountPrice();
			cartItem.setPrice(price);
			
			CartItem createdCartItem= cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}
		return "Item Add To Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId);
		
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		
		
		
		for(CartItem cartItem :cart.getCartItems()) {
			totalPrice=totalPrice+cartItem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountPrice();
			totalItem=totalItem+cartItem.getQuantity();
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscounte(totalDiscountedPrice);
		return cartRepository.save(cart);
	}

}
