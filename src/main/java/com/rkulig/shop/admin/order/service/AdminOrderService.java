package com.rkulig.shop.admin.order.service;

import com.rkulig.shop.admin.order.model.AdminOrder;
import com.rkulig.shop.admin.order.model.AdminOrderLog;
import com.rkulig.shop.admin.order.model.AdminOrderStatus;
import com.rkulig.shop.admin.order.repository.AdminOrderLogRepository;
import com.rkulig.shop.admin.order.repository.AdminOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

import static com.rkulig.shop.admin.order.service.AdminOrderEmailMessage.*;


@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository orderRepository;
    private final AdminOrderLogRepository adminOrderLogRepository;
    private final EmailNotificationForStatusChange emailNotificationForStatusChange;

    public Page<AdminOrder> getOrders(Pageable pageable) {
        return orderRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        Sort.by("id").descending())
        );
    }

    public AdminOrder getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void patchOrder(Long id, Map<String, String> values) {
        AdminOrder adminOrder = orderRepository.findById(id).orElseThrow();
        patchValues(adminOrder, values);
    }

    private void patchValues(AdminOrder adminOrder, Map<String, String> values) {
        if (values.get("orderStatus") != null) {
            processOrderStatusChange(adminOrder, values);
        }
    }

    private void processOrderStatusChange(AdminOrder adminOrder, Map<String, String> values) {
        AdminOrderStatus oldStatus = adminOrder.getOrderStatus();
        AdminOrderStatus newStatus = AdminOrderStatus.valueOf(values.get("orderStatus"));
        adminOrder.setOrderStatus(newStatus);
        logStatusChange(adminOrder.getId(), oldStatus, newStatus);
        emailNotificationForStatusChange.sendEmailNotification(newStatus, adminOrder);
    }

    private void logStatusChange(Long orderId, AdminOrderStatus oldStatus, AdminOrderStatus newStatus) {
        adminOrderLogRepository.save(AdminOrderLog.builder()
                .created(LocalDateTime.now())
                .orderId(orderId)
                .note("Zmiana statusu zamowienia z " + oldStatus.getValue() + "na " + newStatus.getValue())
                .build());
    }
}

