package com.nashtech.adaptive_consistent_hashing_java_demo.config;

import com.nashtech.adaptive_consistent_hashing_java_demo.hashing.*;
import com.nashtech.adaptive_consistent_hashing_java_demo.metrics.MetricsCollector;
import com.nashtech.adaptive_consistent_hashing_java_demo.node.StorageNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HashingConfig {

    @Bean
    public MetricsCollector metricsCollector() {
        return new MetricsCollector();
    }

    @Bean
    public HashRing hashRing() {
        HashRing ring = new HashRing();

        // Simulated cluster nodes
        List<StorageNode> nodes = List.of(
                new StorageNode("node-1"),
                new StorageNode("node-2"),
                new StorageNode("node-3")
        );

        // Assign virtual nodes
        nodes.forEach(node -> ring.addNode(node, 50));

        return ring;
    }

    @Bean
    public AdaptiveRouter adaptiveRouter(
            HashRing hashRing,
            MetricsCollector metricsCollector) {

        return new AdaptiveRouter(hashRing, metricsCollector);
    }
}
