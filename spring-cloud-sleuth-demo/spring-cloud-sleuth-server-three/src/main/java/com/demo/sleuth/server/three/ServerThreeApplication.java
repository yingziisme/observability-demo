package com.demo.sleuth.server.three;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServerThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerThreeApplication.class, args);
    }
}
