package com.rkulig.shop.common.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailClientService {

    @Value("${app.email.sender}")
    private String isFakeProp;

//    private final List<EmailSender> list; // Spring injects all implementation of EmailSender here
    private final Map<String, EmailSender> senderMap; // Spring injects all implementation of EmailSender here as map <beanName, impl>

    public EmailSender getInstance(){
       if (isFakeProp.equals("fakeEmailService")){
           return senderMap.get("fakeEmailService");
       }
       return senderMap.get("emailSimpleService");
    }

    public EmailSender getInstance(String beanName){
        if (isFakeProp.equals("fakeEmailService")){
            return senderMap.get("fakeEmailService");
        }
        return senderMap.get("beanName");
    }
}
