package com.example.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ribbon")
public class RibbonController {


    /**
     * 注入RestTemplate
     */
    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value = "/testRibbon" ,method = RequestMethod.GET)
    public String testRibbon(){
        String url="http://CLOUD-SERVICE/base";  //CLOUD-SERVICE 是服务一和服务二的application.name
        return restTemplate.getForObject(url,String.class);
    }
}
