package com.rkulig.shop.product.repository;

import com.rkulig.shop.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}