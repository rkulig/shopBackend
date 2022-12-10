package com.rkulig.shop.order.service.mapper;

import com.rkulig.shop.common.model.Cart;
import com.rkulig.shop.common.model.CartItem;
import com.rkulig.shop.order.model.Order;
import com.rkulig.shop.order.model.OrderRow;
import com.rkulig.shop.order.model.OrderStatus;
import com.rkulig.shop.order.model.Payment;
import com.rkulig.shop.order.model.Shipment;
import com.rkulig.shop.order.model.dto.OrderDto;
import com.rkulig.shop.order.model.dto.OrderSummary;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {


    public static Order createNewOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment) {
        Order order = Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .payment(payment)
                .build();
        return order;
    }


    public static OrderSummary createOrderSummary(Payment payment, Order newOrder) {
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .payment(payment)
                .build();
    }

    public static OrderRow mapToOrderRow(Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build();
    }

    public static OrderRow mapToOrderRowWithQuantity(Long orderId, CartItem cartItem) {
        return OrderRow.builder()
                .quantity(cartItem.getQuantity())
                .productId(cartItem.getProduct().getId())
                .price(cartItem.getProduct().getPrice())
                .orderId(orderId)
                .build();
    }

    private static BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(shipment.getPrice());
    }
}
