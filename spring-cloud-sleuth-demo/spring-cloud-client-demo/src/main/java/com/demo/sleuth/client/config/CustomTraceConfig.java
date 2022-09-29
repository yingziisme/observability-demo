package com.demo.sleuth.client.config;


import brave.propagation.CurrentTraceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomTraceConfig {

    @Bean
    CurrentTraceContext.Builder sleuthCurrentTraceContextBuilder() {
        return CustomTransmittableCurrentTraceContext.newBuilder();
    }
}
