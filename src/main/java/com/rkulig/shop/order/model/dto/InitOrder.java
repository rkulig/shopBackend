package com.rkulig.shop.order.model.dto;

import com.rkulig.shop.order.model.Shipment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InitOrder {

    private List<Shipment> shipment;

}
