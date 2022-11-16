package com.rkulig.shop.product.service;

import com.rkulig.shop.product.model.Product;
import com.rkulig.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository prductRepository;
    public Page<Product> getProducts(Pageable pageable) {
        return prductRepository.findAll(pageable);
    }

    public Product getProductBySlug(String slug) {
        return prductRepository.findBySlug(slug).orElseThrow();
    }
}