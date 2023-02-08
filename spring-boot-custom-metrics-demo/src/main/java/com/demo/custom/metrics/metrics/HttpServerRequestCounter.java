package com.demo.custom.metrics.metrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class HttpServerRequestCounter {

    private final Map<String, AtomicInteger> countMap = new HashMap<>();

    public void addCount(String url) {

        countMap.putIfAbsent(url, new AtomicInteger());
        if (countMap.containsKey(url)) {
            int count = countMap.get(url).incrementAndGet();
            log.info("url: {}: {}", url, count);
        }
    }

    public Map<String, AtomicInteger> getCountMap(){
        return countMap;
    }
}
