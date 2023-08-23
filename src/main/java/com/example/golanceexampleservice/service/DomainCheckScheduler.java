package com.example.golanceexampleservice.service;

import com.example.golanceexampleservice.client.DomainClient;
import com.example.golanceexampleservice.model.Domain;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainCheckScheduler {
    private final DomainService domainService;
    private final DomainClient domainClient;

    @Scheduled(cron = "0 0 0 * * *") //every day at 00:00:00
    public void checkDomains() {
        List<Domain> domains = domainService.findAll();
        domains.forEach(domain -> {
            domain.setDateUpdated(LocalDateTime.now());
            domain.setResponseCode(domainClient.check(domain.getDomain()));
        });
        domainService.updateDomains(domains);
    }
}
