package com.rkulig.shop.cart.controller;

import com.rkulig.shop.cart.controller.dto.CartSummaryDto;
import com.rkulig.shop.cart.controller.mapper.CartMapper;
import com.rkulig.shop.cart.model.dto.CartProductDto;
import com.rkulig.shop.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    public final CartService cartService;

    @GetMapping("/{id}")
    public CartSummaryDto getCart(@PathVariable Long id){
        return CartMapper.mapToCartSummary(cartService.getCart(id));
    }

    @PutMapping("/{id}")
    public CartSummaryDto addProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto){
        return CartMapper.mapToCartSummary(cartService.addProductToCart(id, cartProductDto));
    }

}
