package com.example.golanceexampleservice.controller;

import com.example.golanceexampleservice.dto.DomainRequest;
import com.example.golanceexampleservice.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain")
@RequiredArgsConstructor
public class DomainController {

    private final DomainService domainService;

    @PostMapping
    public void saveDomain(@RequestBody DomainRequest domainRequest) {
        domainService.saveDomain(domainRequest);
    }
}
