package com.mentor.first_spring_app.Day_3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${app.company}")
    private String company;

    @Value("${app.version}")
    private String version;

    @Value("${app.name}")
    private String name;


    @GetMapping("/info")
    public String info() {
        return company + " - Version " + version;
    }

}
