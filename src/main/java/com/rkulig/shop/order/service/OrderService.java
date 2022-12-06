package com.rkulig.shop.order.service;

import com.rkulig.shop.common.model.Cart;
import com.rkulig.shop.common.model.CartItem;
import com.rkulig.shop.common.repository.CartItemRepository;
import com.rkulig.shop.common.repository.CartRepository;
import com.rkulig.shop.order.model.Order;
import com.rkulig.shop.order.model.OrderRow;
import com.rkulig.shop.order.model.OrderStatus;
import com.rkulig.shop.order.model.dto.OrderDto;
import com.rkulig.shop.order.model.dto.OrderSummary;
import com.rkulig.shop.order.repository.OrderRepository;
import com.rkulig.shop.order.repository.OrderRowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRowRepository orderRowRepository;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        // pobrac koszyk
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        // stworzenie zamowienia z wierszami
        log.info("firstName:" + orderDto.getFirstName() );
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
                .grossValue(calculateGrossValue(cart.getItems()))
                .build();
        Order newOrder = orderRepository.save(order);
        // zapisac zamowienie
        saveOrderRows(cart, newOrder.getId());
        // usunac koszyk
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
        // zwrocic podsumowanie
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValues(newOrder.getGrossValue())
                .build();
    }

    private BigDecimal calculateGrossValue(List<CartItem> items) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void saveOrderRows(Cart cart, Long id) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(id)
                        .build()
                )
                .peek(orderRow -> orderRowRepository.save(orderRow))
                .toList();
    }
}
