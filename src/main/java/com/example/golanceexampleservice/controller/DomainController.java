package com.example.golanceexampleservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain")
public class DomainController {

    @PostMapping
    public void saveDomain(@RequestParam String domain) {
        // TODO
    }
}
