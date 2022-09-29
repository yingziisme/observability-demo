package com.demo.sleuth.client.config;

import brave.propagation.CurrentTraceContext;
import brave.propagation.TraceContext;
import com.alibaba.ttl.TransmittableThreadLocal;

public class CustomTransmittableCurrentTraceContext extends CurrentTraceContext {

    final TransmittableThreadLocal<TraceContext> local;

    static final TransmittableThreadLocal<TraceContext> DEFAULT = new TransmittableThreadLocal<>();

    CustomTransmittableCurrentTraceContext(
            CurrentTraceContext.Builder builder,
            TransmittableThreadLocal<TraceContext> local
    ) {
        super(builder);
        if (local == null) throw new NullPointerException("local == null");
        this.local = local;
    }

    @Override
    public TraceContext get() {
        return local.get();
    }

    @Override
    public Scope newScope(TraceContext currentSpan) {
        final TraceContext previous = local.get();
        local.set(currentSpan);
        class ThreadLocalScope implements Scope {
            @Override
            public void close() {
                local.set(previous);
            }
        }
        Scope result = new ThreadLocalScope();
        return decorateScope(currentSpan, result);
    }


    public static CurrentTraceContext create() {
        return new Builder().build();
    }

    public static CurrentTraceContext.Builder newBuilder() {
        return new Builder();
    }

    static final class Builder extends CurrentTraceContext.Builder {

        @Override
        public CurrentTraceContext build() {
            return new CustomTransmittableCurrentTraceContext(this, DEFAULT);
        }

        Builder() {
        }
    }
}
