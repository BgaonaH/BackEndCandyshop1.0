package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyshop.modal.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
