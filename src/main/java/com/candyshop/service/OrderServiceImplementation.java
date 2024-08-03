package com.candyshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.candyshop.exception.OrderException;
import com.candyshop.model.Address;
import com.candyshop.model.Order;
import com.candyshop.model.User;

@Service
public class OrderServiceImplementation implements OrderService {

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	public OrderServiceImplementation(CartRepository cartrepository,
			CartItemService cartItemService,
			ProductService productService) {
		this.cartItemService=cartItemService;
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
