package com.jk.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.entity.Customer;

public interface CustomerRepositry extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String email);
	
}
