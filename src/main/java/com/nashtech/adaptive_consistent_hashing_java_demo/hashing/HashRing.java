package com.nashtech.adaptive_consistent_hashing_java_demo.hashing;

import com.nashtech.adaptive_consistent_hashing_java_demo.node.StorageNode;

import java.util.*;

public class HashRing {

    private final SortedMap<Integer, VirtualNode> ring = new TreeMap<>();

    public void addNode(StorageNode node, int vNodes) {
        for (int i = 0; i < vNodes; i++) {
            String vnodeId = node.getId() + "-vn-" + i;
            ring.put(hash(vnodeId), new VirtualNode(vnodeId, node));
        }
    }

    public void removeNode(StorageNode node) {
        ring.entrySet().removeIf(
                e -> e.getValue().physicalNode().equals(node)
        );
    }

    public StorageNode getNode(String key) {
        if (ring.isEmpty()) return null;

        int hash = hash(key);
        SortedMap<Integer, VirtualNode> tail = ring.tailMap(hash);
        VirtualNode vnode = tail.isEmpty()
                ? ring.get(ring.firstKey())
                : tail.get(tail.firstKey());

        return vnode.physicalNode();
    }

    public Collection<StorageNode> allNodes() {
        Set<StorageNode> nodes = new HashSet<>();
        ring.values().forEach(vn -> nodes.add(vn.physicalNode()));
        return nodes;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode());
    }
}