package com.jk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.jk.entity.ProductCategory;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(path = "product-category")
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

}
