package com.example.boot.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class HystrixService {

    @HystrixCommand(fallbackMethod = "defaultStores")
    public String getStores() {
        System.out.println("客户端请求的方法");
        int i = 1 / 0;
        return "正常";
    }

    public String defaultStores() {
        System.out.println("客户端请求方法异常，走熔断方法");
        return "熔断";
    }
}
