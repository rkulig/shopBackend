package com.rkulig.shop.common.mail;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import javax.validation.constraints.Email;

//@Configuration
public class EmailConfig {

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", matchIfMissing = true, havingValue = "emailSimpleService")
    public EmailSender emailSimpleService(JavaMailSender javaMailSender){
        return new EmailSimpleService(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", havingValue = "fakeEmailService")
    public EmailSender fakeEmailService(){
        return new FakeEmailService();
    }

//    @Bean
//    @ConditionalOnProperty(name = "app.email.sender", havingValue = "webServiceEmailService")
//    public EmailSender webServiceEmailService(){
//        return new WebServiceEmailService();
//    }
}
