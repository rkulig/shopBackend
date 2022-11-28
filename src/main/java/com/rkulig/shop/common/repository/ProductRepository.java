package com.rkulig.shop.common.repository;

import com.rkulig.shop.common.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySlug(String slug); // query methods

    Page<Product> findByCategoryId(Long id, Pageable pageable);
}