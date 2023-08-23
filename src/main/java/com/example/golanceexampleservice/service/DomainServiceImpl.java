package com.example.golanceexampleservice.service;

import com.example.golanceexampleservice.client.DomainClient;
import com.example.golanceexampleservice.client.SlackBotClient;
import com.example.golanceexampleservice.model.Domain;
import com.example.golanceexampleservice.repository.DomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService{

    private final DomainRepository domainRepository;
    private final DomainClient domainClient;
    private final SlackBotClient slackBotClient;
    private final Integer OK_RESPONSE_CODE = 520;

    public void saveDomain(String domainName) {
        Optional<Domain> oldDomainRecord = domainRepository.findByDomain(domainName);
        Integer responseCode = domainClient.check(domainName);
        Domain domain = oldDomainRecord.orElse(new Domain());
        domain.setResponseCode(responseCode);
        LocalDateTime now = LocalDateTime.now();
        domain.setDateUpdated(now);
        if (oldDomainRecord.isEmpty()) {
            domain.setDateCreated(now);
        }
        domainRepository.save(domain);

        if (OK_RESPONSE_CODE.equals(responseCode)) {
            slackBotClient.notify(String.format("Domain %s successfully checked", domainName));
        }

    }
}
