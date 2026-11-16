package com.mentor.first_spring_app.Day_3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String appName(){
        return "My First Spring App";
    }

}
