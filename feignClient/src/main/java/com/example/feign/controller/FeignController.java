package com.example.feign.controller;

import com.example.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/api")
public class FeignController {
    @Autowired
    private FeignService feignService;

    @RequestMapping("/base")
    public String base() {
        System.out.println("feign 测试");
        return feignService.getFeignContent();
    }
}
