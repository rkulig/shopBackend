package com.rkulig.shop.common.mail;

public interface EmailSender {

    void send(String to, String subject, String msg);

}
