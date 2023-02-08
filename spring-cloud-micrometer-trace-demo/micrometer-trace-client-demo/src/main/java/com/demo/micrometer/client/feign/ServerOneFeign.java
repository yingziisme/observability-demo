package com.demo.micrometer.client.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "micrometer-trace-server-one")
public interface ServerOneFeign {

    @GetMapping("/echo/{param}")
    String echo(@PathVariable String param);
}
