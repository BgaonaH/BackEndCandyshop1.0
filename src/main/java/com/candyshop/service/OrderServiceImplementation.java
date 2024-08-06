package com.candyshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.candyshop.exception.OrderException;
import com.candyshop.model.Address;
import com.candyshop.model.Order;
import com.candyshop.model.User;
import com.candyshop.repository.CartRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	private CartRepository cartRepository;
	private CartService cartService;
	private ProductService productService;
	
	public OrderServiceImplementation(CartRepository cartrepository,
			CartService cartService,
			ProductService productService) {
		this.cartService=cartService;
		this.cartRepository=cartRepository;
		this.productService=productService;
	}
	 
	@Override
	public Order createOrder(User user, Address shippingAdress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

}
