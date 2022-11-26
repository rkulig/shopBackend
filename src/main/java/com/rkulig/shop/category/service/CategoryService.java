package com.rkulig.shop.category.service;

import com.rkulig.shop.category.model.Category;
import com.rkulig.shop.category.model.CategoryProductsDto;
import com.rkulig.shop.category.repository.CategoryRepository;
import com.rkulig.shop.common.dto.ProductListDto;
import com.rkulig.shop.product.model.Product;
import com.rkulig.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true) // dzieki tej adnotacji robimy jedna tranzakcje zamiast dwoch,czyli troche szybciej, readOnly dlatego ze niczego nie zapisujemy
    public CategoryProductsDto getCategoriesWithProducts(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug);
        Page<Product> page = productRepository.findByCategoryId(category.getId(), pageable);
        List<ProductListDto> productListDtos = page.getContent().stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .currency(product.getCurrency())
                        .image(product.getImage())
                        .slug(product.getSlug())
                        .build())
                .toList();
        return new CategoryProductsDto(category,new PageImpl<>(productListDtos,pageable,page.getTotalElements()));
    }
}
