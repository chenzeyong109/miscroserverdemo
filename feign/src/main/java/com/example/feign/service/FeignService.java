package com.example.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//测试feign接口服务
@FeignClient("CLOUD-SERVICE")  //服务名不区分大小写，小写也可以cloud-service
public interface FeignService {

    /**
     * 调取博客名称
     *
     * @return 博客名称
     */
    @GetMapping("/feign/feignTest")
    String getFeignContent();
}
