package com.candyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyshop.modal.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
