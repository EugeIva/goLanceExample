package com.example.golanceexampleservice.controller;

import com.example.golanceexampleservice.dto.DomainRequest;
import com.example.golanceexampleservice.model.Domain;
import com.example.golanceexampleservice.service.DomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DomainController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DomainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DomainService domainService;

    @Test
    public void testGetDomains() throws Exception {

        Domain domain = Domain.builder()
                .id(UUID.randomUUID())
                .domain("test")
                .responseCode(123)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();
        List<Domain> domains = List.of(domain);
        when(domainService.findAll()).thenReturn(domains);

        mockMvc.perform(get("/domain"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("domains", domains))
                .andExpect(view().name("index"));

        verify(domainService, times(1)).findAll();
    }
    @Test
    public void testSaveDomain() throws Exception {
        DomainRequest domainRequest = new DomainRequest("test");
        doNothing().when(domainService).saveDomain(any(DomainRequest.class));

        mockMvc.perform(post("/domain")
                        .flashAttr("domainRequest", domainRequest))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        verify(domainService, times(1)).saveDomain(any(DomainRequest.class));
    }
}