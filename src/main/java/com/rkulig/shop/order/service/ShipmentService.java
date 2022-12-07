package com.rkulig.shop.order.service;

import com.rkulig.shop.order.model.Shipment;
import com.rkulig.shop.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public List<Shipment> getShipments(){
        return shipmentRepository.findAll();
    }
}
