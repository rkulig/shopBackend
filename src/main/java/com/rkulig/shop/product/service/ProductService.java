package com.rkulig.shop.product.service;

import com.rkulig.shop.common.model.Product;
import com.rkulig.shop.common.model.Review;
import com.rkulig.shop.common.repository.ProductRepository;
import com.rkulig.shop.common.repository.ReviewRepository;
import com.rkulig.shop.product.service.dto.ProductDto;
import com.rkulig.shop.product.service.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository prductRepository;
    private final ReviewRepository reviewRepository;
    public Page<Product> getProducts(Pageable pageable) {
        return prductRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductBySlug(String slug) {
        Product product = prductRepository.findBySlug(slug).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProductIdAndModerated(product.getId(), true);
        return mapToProductDto(product, reviews);
    }

    private ProductDto mapToProductDto(Product product, List<Review> reviews) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .reviews(reviews.stream().map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .productId(review.getProductId())
                        .authorName(review.getAuthorName())
                        .content(review.getContent())
                        .moderate(review.isModerated())
                        .build())
                        .toList())
                .build();
    }
}