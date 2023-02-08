package com.demo.actuator.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "actuator-server")
public interface ActuatorServerFeign {

    @GetMapping("/echo/{param}")
    String echo(@PathVariable String param);
}
