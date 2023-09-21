package com.example.demoThymeleaf.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoThymeleaf.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByProductName(String productName);
}
