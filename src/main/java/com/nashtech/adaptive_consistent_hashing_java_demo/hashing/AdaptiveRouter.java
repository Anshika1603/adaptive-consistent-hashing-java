package com.nashtech.adaptive_consistent_hashing_java_demo.hashing;

import com.nashtech.adaptive_consistent_hashing_java_demo.metrics.MetricsCollector;
import com.nashtech.adaptive_consistent_hashing_java_demo.node.StorageNode;

import java.util.List;
import java.util.Random;

public class AdaptiveRouter {

    private final HashRing ring;
    private final MetricsCollector metrics;
    private final Random random = new Random();

    public AdaptiveRouter(HashRing ring, MetricsCollector metrics) {
        this.ring = ring;
        this.metrics = metrics;
    }

    public StorageNode route(String key) {
        metrics.recordKey(key);

        StorageNode node = ring.getNode(key);

        // Hot-key replication
        if (metrics.isHotKey(key)) {
            List<StorageNode> nodes = List.copyOf(ring.allNodes());
            return nodes.get(random.nextInt(nodes.size()));
        }

        // Load-aware routing
        if (metrics.isOverloaded(node)) {
            return ring.allNodes().iterator().next();
        }

        return node;
    }
}
