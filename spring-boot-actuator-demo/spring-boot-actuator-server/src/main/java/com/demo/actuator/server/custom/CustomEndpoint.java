package com.demo.actuator.server.custom;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Endpoint(id = "custom", enableByDefault = true)
public class CustomEndpoint {

    @ReadOperation
    public Map<String, Object> getCustomInfo() {

        Map<String, Object> result = new HashMap<>();
        result.put("custom", 1);
        return result;

    }
}
