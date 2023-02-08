package com.demo.otel.server.one.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "provider-demo")
public interface ProviderDemoFeign {
    @RequestMapping(value = "/echo/{param}", method = RequestMethod.GET)
    String echo(@PathVariable("param") String param);

}
