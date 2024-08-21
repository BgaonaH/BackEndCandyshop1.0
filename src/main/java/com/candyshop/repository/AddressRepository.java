package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyshop.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
