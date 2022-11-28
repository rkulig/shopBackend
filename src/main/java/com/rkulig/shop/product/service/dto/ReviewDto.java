package com.rkulig.shop.product.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDto {
    private Long id;
    private Long productId;
    private String authorName;
    private String content;
    private boolean moderate;
}
