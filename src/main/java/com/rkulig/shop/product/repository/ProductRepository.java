package com.rkulig.shop.product.repository;

import com.rkulig.shop.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySlug(String slug); // query methods

    Page<Product> findByCategoryId(Long id, Pageable pageable);
}