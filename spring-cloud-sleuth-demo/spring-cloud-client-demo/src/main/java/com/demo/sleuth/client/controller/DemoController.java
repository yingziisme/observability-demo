package com.demo.sleuth.client.controller;


import brave.Tracing;
import brave.propagation.CurrentTraceContext;
import com.demo.sleuth.client.feign.ServerOneFeign;
import com.demo.sleuth.client.feign.ServerThreeFeign;
import com.demo.sleuth.client.feign.ServerTwoFeign;
import com.demo.sleuth.client.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    private DemoService demoService;

    @Autowired
    private Tracing tracing;

    @Autowired
    private CurrentTraceContext context;


    @GetMapping("/parallelStream/{param}")
    public void parallelStream(@PathVariable String param) {
        List<String> paramList = new ArrayList<>();
        for (int i = 0; i < param.length(); i++) {
            paramList.add(String.valueOf(param.charAt(i)));
        }
        demoService.parallelStream(paramList);
    }

    @GetMapping("/traceableSubmit/{param}")
    public void traceableSubmit(@PathVariable String param) {
        log.info("traceableSubmit: {}", param);
        demoService.traceSubmit(param);
    }

    @GetMapping("/traceableExecutor/{param}")
    public String traceableExecutor(@PathVariable String param) throws ExecutionException, InterruptedException {
        log.info("traceableExecutor: {}", param);
        return demoService.traceableExecutor(param);
    }

    @GetMapping("/async/{param}")
    public void async(@PathVariable String param) {
        log.info("async: {}", param);
        demoService.async(param);
    }

    @GetMapping("/ttlExecutor/{param}")
    public void ttlExecutor(@PathVariable String param) {
        log.info("ttlExecutor: {}", param);
        demoService.ttlExecutor(param);
    }


    @GetMapping("/executor/{param}")
    public void executor(@PathVariable String param) {
        log.info("executor: {}", param);
        demoService.executor(param);
    }


    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {
        Class cls = context.getClass();
        log.info("context.getClass(): {}", context.getClass());
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
