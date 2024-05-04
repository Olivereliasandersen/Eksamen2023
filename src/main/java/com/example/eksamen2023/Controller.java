package com.example.eksamen2023;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping ("/hello")
    public String check(){
        return "Your controller is working";
    }
}
