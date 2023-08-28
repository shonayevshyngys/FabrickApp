package com.github.fabrickapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FabrickController {

    @GetMapping("/balance")
    public ResponseEntity<String> allLocations()
    {
        return null;
    }
}
