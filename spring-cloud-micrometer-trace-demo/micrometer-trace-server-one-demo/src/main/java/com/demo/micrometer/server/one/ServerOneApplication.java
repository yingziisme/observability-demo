package com.demo.micrometer.server.one;


import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.observation.HttpRequestsObservationFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class ServerOneApplication {

//    @Bean
//    public HttpTraceRepository inMemoryHttpTraceRepository() {
//        InMemoryHttpTraceRepository httpTraceRepository = new InMemoryHttpTraceRepository();
//        httpTraceRepository.setCapacity(2);
////        Observation observation = Observation.start("", httpTraceRepository);
//        return httpTraceRepository;
//    }

    @Bean
    public FilterRegistrationBean traceWebFilter(ObservationRegistry observationRegistry) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HttpRequestsObservationFilter(observationRegistry));
        filterRegistrationBean.setDispatcherTypes(DispatcherType.ASYNC, DispatcherType.ERROR, DispatcherType.FORWARD,
                DispatcherType.INCLUDE, DispatcherType.REQUEST);
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return filterRegistrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerOneApplication.class, args);
    }
}
