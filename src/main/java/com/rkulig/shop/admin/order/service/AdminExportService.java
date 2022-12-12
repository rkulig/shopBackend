package com.rkulig.shop.admin.order.service;

import com.rkulig.shop.admin.category.repository.AdminCategoryRepository;
import com.rkulig.shop.admin.order.model.AdminOrder;
import com.rkulig.shop.admin.order.model.AdminOrderStatus;
import com.rkulig.shop.admin.order.repository.AdminOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminExportService {

    private final AdminOrderRepository adminOrderRepository;

    public List<AdminOrder> exportOrders(LocalDateTime from, LocalDateTime to, AdminOrderStatus orderStatus) {
        return adminOrderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(from, to, orderStatus);
    }
}
