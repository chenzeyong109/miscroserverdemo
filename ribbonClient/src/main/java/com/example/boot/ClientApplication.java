package com.example.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient  //eureka 客户端
@EnableCircuitBreaker  //hystrix 熔断配置
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
    /**
     * Spring提供的用于访问Rest服务的客户端
     * @return
     */
    @Bean
    @LoadBalanced   //Ribbon 负载均衡，在接口调用者配置，负载的服务在eureka里具有相同的服务名
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
