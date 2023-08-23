package com.example.golanceexampleservice.service;


import com.example.golanceexampleservice.dto.DomainRequest;
import com.example.golanceexampleservice.model.Domain;

import java.util.List;

public interface DomainService {
    void saveDomain(DomainRequest domainRequest);
    List<Domain> findAll();

    void updateDomains(List<Domain> domains);
}
