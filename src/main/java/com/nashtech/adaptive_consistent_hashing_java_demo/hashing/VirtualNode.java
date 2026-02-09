package com.nashtech.adaptive_consistent_hashing_java_demo.hashing;

import com.nashtech.adaptive_consistent_hashing_java_demo.node.StorageNode;

public record VirtualNode(String id, StorageNode physicalNode) {
}

