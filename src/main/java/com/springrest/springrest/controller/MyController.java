package com.springrest.springrest.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/load")
public class MyController {

    @Autowired
    private LoadService loadService; // Inject LoadService

    @PostMapping
    public ResponseEntity<String> addLoad(@RequestBody LoadRequest loadRequest) {
        String message = loadService.addLoad(loadRequest); // Call the service method
        return ResponseEntity.ok(message); // Return the success message
    }

    @GetMapping
    public ResponseEntity<List<LoadResponse>> getLoads(@RequestParam String shipperId) {
        List<LoadResponse> loads = loadService.getLoadsByShipperId(UUID.fromString(shipperId)); // Call the service method
        return ResponseEntity.ok(loads);
    }
    @GetMapping("/{loadId}")
    public ResponseEntity<LoadResponse> getLoadById(@PathVariable UUID loadId) {
        LoadResponse load = loadService.getLoadById(loadId);
        if (load != null) {
            return ResponseEntity.ok(load);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<String> updateLoad(@PathVariable UUID loadId, @RequestBody LoadRequest loadRequest) {
        boolean updated = loadService.updateLoad(loadId, loadRequest);
        if (updated) {
            return ResponseEntity.ok("Load details updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<String> deleteLoad(@PathVariable UUID loadId) {
        boolean deleted = loadService.deleteLoad(loadId);
        if (deleted) {
            return ResponseEntity.ok("Load deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
