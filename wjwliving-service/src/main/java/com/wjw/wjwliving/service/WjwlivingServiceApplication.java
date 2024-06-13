package com.wjw.wjwliving.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WjwlivingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WjwlivingServiceApplication.class, args);
    }
}