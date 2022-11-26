package com.rkulig.shop.category.model;

import com.rkulig.shop.common.dto.ProductListDto;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
