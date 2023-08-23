package com.example.golanceexampleservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Domain {
    @Id
    @GeneratedValue
    private UUID id;
    private String domain;
    private Integer responseCode;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
