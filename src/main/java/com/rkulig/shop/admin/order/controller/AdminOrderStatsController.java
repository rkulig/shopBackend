package com.rkulig.shop.admin.order.controller;

import com.rkulig.shop.admin.order.model.dto.AdminOrderStats;
import com.rkulig.shop.admin.order.service.AdminOrderStatsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders/stats")
@RequiredArgsConstructor
public class AdminOrderStatsController {

    private final AdminOrderStatsService adminOrderStatsService;

    @GetMapping
    public AdminOrderStats getOrderStatistics(){
        return adminOrderStatsService.getStatistics();
    }
}
