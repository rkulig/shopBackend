package com.rkulig.shop.cart.service;

import com.rkulig.shop.cart.model.Cart;
import com.rkulig.shop.cart.repository.CartItemRepository;
import com.rkulig.shop.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartCleanupService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemrepository;

    @Transactional
    @Scheduled(cron = "${app.cart.cleanup.expression}")
    public void cleanupOldCarts(){
        List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
        List<Long> ids = carts.stream()
                .map(cart -> cart.getId())
                .toList();
        log.info("Stare koszyki " + carts.size());
//        carts.forEach(cart -> {
//            cartItemrepository.deleteByCartId(cart.getId());
//            cartRepository.deleteCartById(cart.getId());
//        });
        if (!ids.isEmpty()){
            cartItemrepository.deleteAllByCartIdIn(ids);
            cartRepository.deleteAllByIdIn(ids);
        }
    }

}
