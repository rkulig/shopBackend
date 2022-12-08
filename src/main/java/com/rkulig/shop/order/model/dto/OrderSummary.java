package com.rkulig.shop.order.model.dto;

import com.rkulig.shop.order.model.OrderStatus;
import com.rkulig.shop.order.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class OrderSummary {

    private Long id;
    private LocalDateTime placeDate;
    private OrderStatus status;
    private BigDecimal grossValue;
    private Payment payment;

}
