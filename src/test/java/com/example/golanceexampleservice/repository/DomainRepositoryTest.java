package com.example.golanceexampleservice.repository;

import com.example.golanceexampleservice.model.Domain;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DomainRepositoryTest {
    private final TestEntityManager entityManager;
    private final DomainRepository domainRepository;
    @Test
    void findByDomain() {
        Domain domain = new Domain();
        domain.setDomain("test");
        entityManager.persist(domain);
        entityManager.flush();

        Domain found = domainRepository.findByDomain("test").get();

        assertEquals(domain, found);


    }
}