package com.mentor.first_spring_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private EmailService emailService;
    private final SmsService smsService;

    public OrderService(EmailService emailService,SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public String placeOrder(){
        emailService.send("Your order has been placed");
        smsService.send("Your Order has been Placed ");
        return "Order Placed Successfully";
    }

}
