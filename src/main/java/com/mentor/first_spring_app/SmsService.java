package com.mentor.first_spring_app;

import org.springframework.stereotype.Service;

@Service
public class SmsService {
    public void send(String message){
        System.out.println("Sms Sent:" + message);
    }

}
