package com.rkulig.shop.admin.order.service;

import com.rkulig.shop.admin.order.model.AdminOrder;
import com.rkulig.shop.admin.order.model.AdminOrderStatus;
import com.rkulig.shop.common.mail.EmailClientService;
import com.rkulig.shop.common.mail.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.rkulig.shop.admin.order.service.AdminOrderEmailMessage.createCompetedEmailMessage;
import static com.rkulig.shop.admin.order.service.AdminOrderEmailMessage.createProcessingEmailMessage;
import static com.rkulig.shop.admin.order.service.AdminOrderEmailMessage.createRefundEmailMessage;

@Service
@RequiredArgsConstructor
class EmailNotificationForStatusChange {

    private final EmailClientService emailClientService;

    void sendEmailNotification(AdminOrderStatus newStatus, AdminOrder adminOrder) {

        if (newStatus == AdminOrderStatus.PROCESSING){
            sendEmail(adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + " zmieniło status na " + newStatus.getValue(),
                    createProcessingEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus == AdminOrderStatus.COMPLETED){
            sendEmail(adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + " zostało zrealizowane",
                    createCompetedEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus == AdminOrderStatus.REFUND){
            sendEmail(adminOrder.getEmail(),
                    "Zamówienie " + adminOrder.getId() + " zostało zrealizowane",
                    createRefundEmailMessage(adminOrder.getId(), newStatus));

        }
    }

    private void sendEmail(String email, String subject, String message) {
        EmailSender emailSender = emailClientService.getInstance();
        emailSender.send(email, subject, message);
    }

}
