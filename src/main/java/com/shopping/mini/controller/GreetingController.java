package com.shopping.mini.controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-27
 */
@RestController
@CrossOrigin()
public class GreetingController {
    @GetMapping(value = "/greeting")
    public String getEmployees() {
        return "Welcome!";
    }
}