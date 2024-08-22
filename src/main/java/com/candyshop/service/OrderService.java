package com.candyshop.service;

import java.util.List;

import com.candyshop.exception.OrderException;
import com.candyshop.modal.Address;
import com.candyshop.modal.Order;
import com.candyshop.modal.User;

public interface OrderService {

public Order createOrder(User user, Address shippingAdress);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placedOrder(Long orderId) throws OrderException;
	
	public Order confirmedOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order canceledOrder(Long orderId) throws OrderException;
	
}
