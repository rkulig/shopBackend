package com.rkulig.shop.cart.service;

import com.rkulig.shop.cart.repository.CartItemrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemrepository cartItemrepository;

    public void deleteCartItem(Long id){
        cartItemrepository.deleteById(id);
    }

}
