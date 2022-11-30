package com.rkulig.shop.cart.repository;

import com.rkulig.shop.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemrepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cartId);
}
