package com.example.boot.controller;

import com.example.boot.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hystrix")
@RestController
public class HystrixDemoController {

    @Autowired
    private HystrixService hystrixService;

    @GetMapping("/test")
    public String hystrixTest() {
        return hystrixService.getStores();

    }
}
