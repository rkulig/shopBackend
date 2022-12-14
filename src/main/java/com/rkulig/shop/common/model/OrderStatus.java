package com.rkulig.shop.common.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    NEW("Nowe"),
    PAID("Opłacone"),
    PROCESSING("Przetwarzane"),
    WAITING_FOR_DELIVERY("Czeka na dostawę"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane"),
    REFUND("Zwrócone");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
