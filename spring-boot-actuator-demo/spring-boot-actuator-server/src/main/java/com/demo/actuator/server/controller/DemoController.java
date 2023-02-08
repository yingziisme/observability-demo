package com.demo.actuator.server.controller;


import com.demo.actuator.server.custom.CustomMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private CustomMetrics customMetrics;

    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {
        customMetrics.add();
        log.info("echo: {}", param);
        return param;
    }
}
