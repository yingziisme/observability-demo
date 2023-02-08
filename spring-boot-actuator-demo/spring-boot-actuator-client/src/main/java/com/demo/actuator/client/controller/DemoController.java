package com.demo.actuator.client.controller;


import com.demo.actuator.client.feign.ActuatorServerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private ActuatorServerFeign actuatorServerFeign;

    @GetMapping("/test/{param}")
    public String test(@PathVariable String param) {
        log.info("test: {}", param);
        return param;
    }

    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {
        log.info("echo: {}", param);
        return actuatorServerFeign.echo(param);
    }
}
