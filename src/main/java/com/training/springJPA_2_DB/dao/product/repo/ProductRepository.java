package com.training.springJPA_2_DB.dao.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.springJPA_2_DB.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}