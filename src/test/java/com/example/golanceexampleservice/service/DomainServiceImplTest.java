package com.example.golanceexampleservice.service;

import com.example.golanceexampleservice.client.DomainClient;
import com.example.golanceexampleservice.client.SlackBotClient;
import com.example.golanceexampleservice.dto.DomainRequest;
import com.example.golanceexampleservice.model.Domain;
import com.example.golanceexampleservice.repository.DomainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainServiceImplTest {

    @InjectMocks
    private DomainServiceImpl domainService;
    @Mock
    private DomainRepository domainRepository;
    @Mock
    private DomainClient domainClient;
    @Mock
    private SlackBotClient slackBotClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSaveDomain() {
        String domainName = "example.com";
        Integer responseCode = 520;
        DomainRequest domainRequest = new DomainRequest(domainName);

        when(domainRepository.findByDomain(domainName)).thenReturn(Optional.empty());
        when(domainClient.check(domainName)).thenReturn(responseCode);

        domainService.saveDomain(domainRequest);

        verify(domainRepository, times(1)).save(any(Domain.class));
        verify(slackBotClient, times(1)).notify(String.format("Domain %s successfully checked", domainName));
    }

    @Test
    public void testFindAll() {
        List<Domain> domains = List.of(new Domain());
        when(domainRepository.findAll()).thenReturn(domains);

        List<Domain> result = domainService.findAll();

        assertEquals(domains, result);
    }

    @Test
    public void testUpdateDomains() {
        List<Domain> domains = List.of(new Domain());

        domainService.updateDomains(domains);

        verify(domainRepository, times(1)).saveAll(domains);
    }
}




