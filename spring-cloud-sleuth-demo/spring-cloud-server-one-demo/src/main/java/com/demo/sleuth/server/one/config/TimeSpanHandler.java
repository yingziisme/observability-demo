package com.demo.sleuth.server.one.config;

import brave.handler.MutableSpan;
import brave.handler.SpanHandler;
import brave.internal.Nullable;
import brave.propagation.TraceContext;
import org.springframework.stereotype.Service;

//@Service
//public class TimeSpanHandler extends SpanHandler {
//
//    @Override
//    public boolean begin(TraceContext context, MutableSpan span, @Nullable TraceContext parent) {
//        return true;
//    }
//
//    @Override
//    public boolean end(TraceContext context, MutableSpan span, Cause cause) {
//        if (span.finishTimestamp() - span.startTimestamp() > 10000){
//            return true;
//        }
//        return false;
//    }
//}
