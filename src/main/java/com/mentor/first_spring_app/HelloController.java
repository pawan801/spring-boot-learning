package com.mentor.first_spring_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String appName;

    public HelloController(String appName) {
        this.appName = appName;
    }


    @GetMapping("/hello")
    public String sayHello(){

        return "Hello from " + appName;
    }

    @GetMapping("/about")
public String about(){
        return "Pawan Shanaka";
}


}
