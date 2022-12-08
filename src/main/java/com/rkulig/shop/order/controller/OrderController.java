package com.rkulig.shop.order.controller;

import com.rkulig.shop.order.model.dto.InitOrder;
import com.rkulig.shop.order.model.dto.OrderDto;
import com.rkulig.shop.order.model.dto.OrderSummary;
import com.rkulig.shop.order.service.OrderService;
import com.rkulig.shop.order.service.PaymentService;
import com.rkulig.shop.order.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    @PostMapping
    public OrderSummary placeOrder(@RequestBody OrderDto orderDto){
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("initData")
    public InitOrder initData(){
        return InitOrder.builder()
                .shipment(shipmentService.getShipments())
                .payment(paymentService.getPayments())
                .build();
    }
}
