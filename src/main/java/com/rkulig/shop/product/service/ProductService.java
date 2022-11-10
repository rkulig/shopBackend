package com.rkulig.shop.product.service;

import com.rkulig.shop.product.model.Product;
import com.rkulig.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository prductRepository;
    public List<Product> getProducts() {
        return prductRepository.findAll();
    }

}