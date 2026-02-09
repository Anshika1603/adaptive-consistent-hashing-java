package com.nashtech.adaptive_consistent_hashing_java_demo.node;

import java.util.concurrent.atomic.AtomicInteger;

public class StorageNode {

    private final String id;
    private final AtomicInteger load = new AtomicInteger(0);

    public StorageNode(String id) {
        this.id = id;
    }

    public void handle() {
        load.incrementAndGet();
        try {
            Thread.sleep(5); // simulate work
        } catch (InterruptedException ignored) {}
        load.decrementAndGet();
    }

    public int getLoad() {
        return load.get();
    }

    public String getId() {
        return id;
    }
}
