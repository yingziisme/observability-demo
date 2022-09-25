package com.demo.sleuth.server.two.controller;


import com.demo.sleuth.server.two.feign.ServerOneFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private ServerOneFeign serverOneFeign;

    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {

        log.info("from server: {}, param is: {}", applicationName, param);
        return applicationName + ":" + param;
    }

    @GetMapping("/one/{param}")
    public String one(@PathVariable String param) {

        log.info("from server: {}, param is: {}", applicationName, param);
        return serverOneFeign.echo(param);
    }
}
