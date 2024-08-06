package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyshop.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{

}
