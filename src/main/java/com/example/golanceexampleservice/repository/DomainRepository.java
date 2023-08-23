package com.example.golanceexampleservice.repository;

import com.example.golanceexampleservice.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DomainRepository extends JpaRepository<Domain, UUID> {

    Optional<Domain> findByDomain(String domain);

}
