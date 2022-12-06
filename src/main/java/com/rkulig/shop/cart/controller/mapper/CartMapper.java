package com.rkulig.shop.cart.controller.mapper;

import com.rkulig.shop.cart.controller.dto.CartSummaryDto;
import com.rkulig.shop.cart.controller.dto.CartSummaryItemDto;
import com.rkulig.shop.cart.controller.dto.ProductDto;
import com.rkulig.shop.cart.controller.dto.SummaryDto;
import com.rkulig.shop.common.model.Cart;
import com.rkulig.shop.common.model.CartItem;
import com.rkulig.shop.common.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class CartMapper {
    public static CartSummaryDto mapToCartSummary(Cart cart) {
        return CartSummaryDto.builder()
                .id(cart.getId())
                .items(mapCartItems(cart.getItems()))
                .summary(mapToSummaryDto(cart.getItems()))
                .build();
    }

    private static List<CartSummaryItemDto> mapCartItems(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::mapToCartItem)
                .toList();
    }

    private static CartSummaryItemDto mapToCartItem(CartItem cartItem) {
        return CartSummaryItemDto.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(mapToProductDto(cartItem.getProduct()))
                .lineValue(calculateLIneValue(cartItem))
                .build();
    }

    private static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .build();
    }

    private static BigDecimal calculateLIneValue(CartItem cartItem) {
        return cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
    private static SummaryDto mapToSummaryDto(List<CartItem> items) {
        return SummaryDto.builder()
                .grossValue(sumValues(items))
                .build();
    }

    private static BigDecimal sumValues(List<CartItem> items) {
        return items.stream()
                .map(item -> calculateLIneValue(item))
                .reduce((bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2))
                .orElse(BigDecimal.ZERO);
    }
}
