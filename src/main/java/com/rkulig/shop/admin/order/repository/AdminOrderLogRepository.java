package com.rkulig.shop.admin.order.repository;

import com.rkulig.shop.admin.order.model.AdminOrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long> {
}
