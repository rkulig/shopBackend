package com.rkulig.shop.admin.order.service;

import com.rkulig.shop.common.model.OrderStatus;

public class AdminOrderEmailMessage {

    public static String createProcessingEmailMessage(Long id, OrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " jest przetwarzane." +
                "\nStatus został zmieniony na: " + newStatus.getValue() +
                "\nTwoje zamówienie jest przetwarzane przez naszych pracowników" +
                "\nPo skompletowaniu niezwłocznie przekażemy je do wysyłki" +
                "\n\n Pozdrawiamy" +
                "\n Sklep Shop";

    }

    public static String createCompetedEmailMessage(Long id, OrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zrealizowane." +
                "\nStatus został zmieniony na: " + newStatus.getValue() +
                "\n\nDziękujemy za zakupy i zapraszamy ponownie" +
                "\n Sklep Shop";
    }

    public static String createRefundEmailMessage(Long id, OrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zwrócone." +
                "\nStatus został zmieniony na: " + newStatus.getValue() +
                "\n\nDziękujemy za zakupy i zapraszamy ponownie" +
                "\n Sklep Shop";
    }
}
