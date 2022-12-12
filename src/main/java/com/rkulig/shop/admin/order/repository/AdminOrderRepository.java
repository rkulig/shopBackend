package com.rkulig.shop.admin.order.repository;

import com.rkulig.shop.admin.order.model.AdminOrder;
import com.rkulig.shop.admin.order.model.AdminOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
    List<AdminOrder> findAllByPlaceDateIsBetweenAndOrderStatus(LocalDateTime from, LocalDateTime to, AdminOrderStatus orderStatus);
}
