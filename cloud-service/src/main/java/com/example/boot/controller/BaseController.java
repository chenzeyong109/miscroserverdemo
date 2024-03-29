package com.example.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
@RefreshScope
public class BaseController {
    @Value("${env}")
    private String env;

    @GetMapping(value = "/index")
    public String base() {
        System.out.println("base -1 ");
        return "cloud-env:" + env;
    }
}
