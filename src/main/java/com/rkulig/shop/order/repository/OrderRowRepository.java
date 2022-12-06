package com.rkulig.shop.order.repository;

import com.rkulig.shop.order.model.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
