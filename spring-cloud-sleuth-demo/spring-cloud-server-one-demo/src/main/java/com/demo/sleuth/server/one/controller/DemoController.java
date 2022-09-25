package com.demo.sleuth.server.one.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {

        log.info("from server: {}, param is: {}", applicationName, param);
        return applicationName + ":" + param;
    }
}
