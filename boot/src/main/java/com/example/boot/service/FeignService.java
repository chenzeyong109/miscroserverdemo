package com.example.boot.service;

import org.springframework.stereotype.Service;

@Service
public class FeignService {

    public String getFeignContent() {
        System.out.println("feign 模板方法调用");
        return "feign boot";
    }
}
