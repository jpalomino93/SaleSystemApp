package com.tecylab.SaleSystem.repository;

import com.tecylab.SaleSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
