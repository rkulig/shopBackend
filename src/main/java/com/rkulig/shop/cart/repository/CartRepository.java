package com.rkulig.shop.cart.repository;

import com.rkulig.shop.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

import static liquibase.repackaged.net.sf.jsqlparser.parser.feature.Feature.delete;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByCreatedLessThan(LocalDateTime minusDays);

    @Query("delete from Cart c where c.id=:id")
    @Modifying
    void deleteCartById(Long id);

    @Query("delete from Cart c where c.id in (:ids)")
    @Modifying
    void deleteAllByIdIn(List<Long> ids);
}
