package com.nashtech.adaptive_consistent_hashing_java_demo.controller;

import com.nashtech.adaptive_consistent_hashing_java_demo.hashing.AdaptiveRouter;
import com.nashtech.adaptive_consistent_hashing_java_demo.node.StorageNode;
import org.springframework.web.bind.annotation.*;

@RestController
public class HashController {

    private final AdaptiveRouter router;

    public HashController(AdaptiveRouter router) {
        this.router = router;
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        StorageNode node = router.route(key);
        node.handle();
        return "Handled by " + node.getId();
    }
}

