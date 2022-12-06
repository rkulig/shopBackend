package com.rkulig.shop.cart.service;

import com.rkulig.shop.common.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemrepository;

    public void deleteCartItem(Long id){
        cartItemrepository.deleteById(id);
    }

    public Long countItemInCart(Long cartId) {
        return cartItemrepository.countByCartId(cartId);
    }
}
