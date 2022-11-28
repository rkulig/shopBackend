package com.rkulig.shop.category.dto;

import com.rkulig.shop.common.dto.ProductListDto;
import com.rkulig.shop.common.model.Category;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
