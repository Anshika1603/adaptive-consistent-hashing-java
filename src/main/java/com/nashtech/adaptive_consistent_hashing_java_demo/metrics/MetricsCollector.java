package com.nashtech.adaptive_consistent_hashing_java_demo.metrics;

import com.nashtech.adaptive_consistent_hashing_java_demo.node.StorageNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MetricsCollector {

    private static final int HOT_KEY_THRESHOLD = 100;
    private static final int NODE_OVERLOAD = 20;

    private final Map<String, AtomicInteger> keyAccess = new ConcurrentHashMap<>();

    public void recordKey(String key) {
        keyAccess.computeIfAbsent(key, k -> new AtomicInteger()).incrementAndGet();
    }

    public boolean isHotKey(String key) {
        return keyAccess.getOrDefault(key, new AtomicInteger(0)).get() > HOT_KEY_THRESHOLD;
    }

    public boolean isOverloaded(StorageNode node) {
        return node.getLoad() > NODE_OVERLOAD;
    }
}
