package com.example.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseController {

    @RequestMapping
    public String base() {
        System.out.println("base -1 ");
        return "base1";
    }
}
