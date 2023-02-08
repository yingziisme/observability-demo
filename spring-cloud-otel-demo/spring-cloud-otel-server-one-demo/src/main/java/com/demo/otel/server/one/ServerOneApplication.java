package com.demo.otel.server.one;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ServerOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerOneApplication.class, args);
    }
}
