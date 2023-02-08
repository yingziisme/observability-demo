package com.demo.micrometer.server.one.controller;


import io.micrometer.observation.ObservationRegistry;
import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.observation.HttpRequestsObservationFilter;

@Slf4j
@RestController
public class DemoController {

//    @Autowired
//    private HttpTraceFilter httpTraceFilter;


//    public DemoController(FilterRegistrationBean filterRegistrationBean, ObservationRegistry observationRegistry) {
////        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new HttpRequestsObservationFilter(observationRegistry));
//        filterRegistrationBean.setDispatcherTypes(DispatcherType.ASYNC, DispatcherType.ERROR, DispatcherType.FORWARD,
//                DispatcherType.INCLUDE, DispatcherType.REQUEST);
//        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
//    }

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {

        log.info("from server: {}, param is: {}", applicationName, param);
        return applicationName + ":" + param;
    }
}
