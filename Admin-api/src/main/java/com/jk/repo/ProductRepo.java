package com.jk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jk.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
