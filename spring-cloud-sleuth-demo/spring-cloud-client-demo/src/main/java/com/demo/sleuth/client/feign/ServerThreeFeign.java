package com.demo.sleuth.client.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "spring-cloud-sleuth-server-three")
public interface ServerThreeFeign {

    @GetMapping("/echo/{param}")
    String echo(@PathVariable String param);

    @GetMapping("/one/{param}")
    String one(@PathVariable String param);

    @GetMapping("/two/{param}")
    String two(@PathVariable String param);
}
