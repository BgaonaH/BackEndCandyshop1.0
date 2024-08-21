package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyshop.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
