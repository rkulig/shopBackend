package com.rkulig.shop.category.service;

import com.rkulig.shop.category.model.Category;
import com.rkulig.shop.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
