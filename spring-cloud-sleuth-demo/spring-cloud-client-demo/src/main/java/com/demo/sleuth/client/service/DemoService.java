package com.demo.sleuth.client.service;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.demo.sleuth.client.feign.ServerOneFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@Service
public class DemoService {

    @Autowired
    private ServerOneFeign serverOneFeign;

    @Autowired
    private BeanFactory beanFactory;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private ExecutorService traceableExecutorService;

    private ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(Executors.newCachedThreadPool());

    @PostConstruct
    public void initExecutorService() {
        traceableExecutorService = new TraceableExecutorService(beanFactory, Executors.newCachedThreadPool());
    }

    public void ttlExecutor(String param) {
        ttlExecutorService.submit(() -> {
            log.info("ttlExecutor: {}", param);
            serverOneFeign.echo(param);
        });
    }

    public void parallelStream(List<String> paramList) {
        log.info("parallelStream paramList: {}", paramList);
        paramList.parallelStream().forEach(param -> {
            log.info("parallelStream: {}", param);
            serverOneFeign.echo(param);
        });
    }

    public void executor(String param) {
        executorService.submit(() -> {
            log.info("executor: {}", param);
            serverOneFeign.echo(param);
        });
    }


    public void traceSubmit(String param) {
        traceableExecutorService.submit(() -> {
            log.info("executor: {}", param);
            serverOneFeign.echo(param);
        });
    }

    public String traceableExecutor(String param) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {

            log.info("traceableExecutor: {}", param);
           return serverOneFeign.echo(param);
        }, new TraceableExecutorService(beanFactory, executorService));
        return completableFuture.get();
    }

    @Async
    public void async(String param) {
        serverOneFeign.echo(param);
    }

//    @Scheduled(cron = "*/10 * * * * *")
    public void demoScheduled() {
        String param = String.valueOf(System.currentTimeMillis());
        log.info("executor: {}", param);
        serverOneFeign.echo(param);
    }
}
