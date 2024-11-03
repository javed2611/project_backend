package com.jk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jk.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
