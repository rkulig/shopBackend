package com.rkulig.shop.admin.order.model;

import lombok.Getter;

@Getter
public enum AdminOrderStatus {
    NEW("Nowe"),
    PAID("Opłacone"),
    PROCESSING("Przetwarzane"),
    WAITING_FOR_DELIVERY("Czeka na dostawę"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane"),
    REFUND("Zwrócone");

    private String value;

    AdminOrderStatus(String value) {
        this.value = value;
    }

}
