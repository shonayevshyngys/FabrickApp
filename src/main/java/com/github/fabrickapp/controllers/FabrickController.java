package com.github.fabrickapp.controllers;

import com.github.fabrickapp.client.FabrickClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FabrickController {

    @Autowired
    FabrickClient client;

    @GetMapping("/balance")
    public ResponseEntity<String> allLocations()
    {
        client.getBalance();
        return null;
    }
}
