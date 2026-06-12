package com.crappay.payments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("/hola")
    public String hola() {
        return "Hola Mundo desde Spring Boot!";
    }
}