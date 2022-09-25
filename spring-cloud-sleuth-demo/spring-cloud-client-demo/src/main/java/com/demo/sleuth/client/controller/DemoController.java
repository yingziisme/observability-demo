package com.demo.sleuth.client.controller;


import com.demo.sleuth.client.feign.ServerOneFeign;
import com.demo.sleuth.client.feign.ServerThreeFeign;
import com.demo.sleuth.client.feign.ServerTwoFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private ServerOneFeign serverOneFeign;

    @Autowired
    private ServerTwoFeign serverTwoFeign;

    @Autowired
    private ServerThreeFeign serverThreeFeign;

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

    @GetMapping("/all/{param}")
    public void all(@PathVariable String param) {

        log.info("serverOneFeign.echo: {}", serverOneFeign.echo(param));
        log.info("serverTwoFeign.echo: {}", serverTwoFeign.echo(param));
        log.info("serverTwoFeign.one: {}", serverTwoFeign.one(param));
        log.info("serverThreeFeign.echo: {}", serverThreeFeign.echo(param));
        log.info("serverThreeFeign.one: {}", serverThreeFeign.one(param));
        log.info("serverThreeFeign.one: {}", serverThreeFeign.two(param));
    }
}
