package com.rkulig.shop.admin.order.controller;

import com.rkulig.shop.admin.order.controller.dto.AdminOrderDto;
import com.rkulig.shop.admin.order.controller.mapper.AdminOrderMapper;
import com.rkulig.shop.admin.order.model.AdminOrder;
import com.rkulig.shop.admin.order.service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping
    public Page<AdminOrderDto> getOrders(Pageable pageable) {
        return AdminOrderMapper.mapToPageDtos(adminOrderService.getOrders(pageable));
    }

    @GetMapping("/{id}")
    public AdminOrder getOrders(@PathVariable Long id) {
        return adminOrderService.getOrder(id);
    }
}
