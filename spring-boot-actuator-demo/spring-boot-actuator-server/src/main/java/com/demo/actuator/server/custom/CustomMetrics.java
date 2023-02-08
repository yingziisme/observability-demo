package com.demo.actuator.server.custom;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    private final Counter counter;

    public CustomMetrics(MeterRegistry registry) {
        counter = Counter.builder("custom.metrics")
                .tag("custom", "1")
                .register(registry);
    }

    public void add() {
        counter.increment();
    }
}
