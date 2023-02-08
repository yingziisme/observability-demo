package com.demo.custom.metrics.controller;


import com.demo.custom.metrics.metrics.HttpServerRequestCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private HttpServerRequestCounter httpServerRequestCounter;


    @GetMapping("/echo/{param}")
    public Map<String, AtomicInteger> echo(@PathVariable String param) {
        log.info("echo: {}", param);

        return httpServerRequestCounter.getCountMap();
    }
}
