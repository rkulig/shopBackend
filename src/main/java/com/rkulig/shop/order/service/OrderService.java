package com.rkulig.shop.order.service;

import com.rkulig.shop.common.mail.EmailClientService;
import com.rkulig.shop.common.mail.EmailSender;
import com.rkulig.shop.common.mail.EmailSimpleService;
import com.rkulig.shop.common.model.Cart;
import com.rkulig.shop.common.model.CartItem;
import com.rkulig.shop.common.repository.CartItemRepository;
import com.rkulig.shop.common.repository.CartRepository;
import com.rkulig.shop.order.model.Order;
import com.rkulig.shop.order.model.OrderRow;
import com.rkulig.shop.order.model.OrderStatus;
import com.rkulig.shop.order.model.Payment;
import com.rkulig.shop.order.model.Shipment;
import com.rkulig.shop.order.model.dto.OrderDto;
import com.rkulig.shop.order.model.dto.OrderSummary;
import com.rkulig.shop.order.repository.OrderRepository;
import com.rkulig.shop.order.repository.OrderRowRepository;
import com.rkulig.shop.order.repository.PaymentRepository;
import com.rkulig.shop.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.rkulig.shop.order.service.mapper.OrderMapper.createNewOrder;
import static com.rkulig.shop.order.service.mapper.OrderMapper.createOrderSummary;
import static com.rkulig.shop.order.service.mapper.OrderMapper.mapToOrderRow;
import static com.rkulig.shop.order.service.mapper.OrderMapper.mapToOrderRowWithQuantity;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRowRepository orderRowRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        sendConfirmEmail(newOrder);
        return createOrderSummary(payment, newOrder);
    }

    private void sendConfirmEmail(Order newOrder) {
        emailClientService.getInstance().send(newOrder.getEmail(), "Twoje zamówienie zostało przyjęte", createEmailMessage(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
    }


    private String createEmailMessage(Order order) {
        return "Twoje zamówienie o id: " + order.getId() +
                "\nData złożenia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "\nWartość: " + order.getGrossValue() + " PLN" +
                "\n\n" +
                "\nPłatnosć: " + order.getPayment().getName() + (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\n Dziękujemy za zakupy.";
    }


    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem))
                .peek(orderRow -> orderRowRepository.save(orderRow))
                .toList();
    }

}
