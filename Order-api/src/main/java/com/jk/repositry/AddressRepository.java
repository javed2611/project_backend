package com.jk.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
