package com.demo.otel.server.one.controller;


import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.baggage.BaggageBuilder;
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
    private ProviderDemoFeign providerDemoFeign;

    @GetMapping("/feign-echo/{param}")
    public String echoFeign(@PathVariable String param) {

        log.info("from server: {}, param is: {}", applicationName, param);

        BaggageBuilder builder = Baggage.current().toBuilder();
        builder.put("local-ip", "192.168.0.1");
        Baggage baggage = builder.build();
        providerDemoFeign.echo(param);
        return applicationName + ":" + param;
    }

    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {

        log.info("from server: {}, param is: {}", applicationName, param);

        BaggageBuilder builder = Baggage.current().toBuilder();
        builder.put("local-ip", "192.168.0.1");
        Baggage baggage = builder.build();
        return applicationName + ":" + param;
    }
}
