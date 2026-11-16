package com.mentor.first_spring_app;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void send(String message){
        System.out.println("Email Sent:" + message);

    }

}
