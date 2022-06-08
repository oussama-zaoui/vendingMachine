package com.oussama.vendingmachine.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
public class Controller {

    @GetMapping("/")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/error")
    public String error() {
        return "rout not found";
    }
}
