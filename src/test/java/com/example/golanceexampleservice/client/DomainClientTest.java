package com.example.golanceexampleservice.client;

import lombok.RequiredArgsConstructor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DomainClientTest {

    public static MockWebServer mockBackEnd;
    private final DomainClient domainClient;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void checkSuccess() {
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200));
        assertEquals(200, domainClient.check(baseUrl));
    }

    @Test
    void checkFail() {
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        mockBackEnd.enqueue(new MockResponse().setResponseCode(400));
        assertEquals(400, domainClient.check(baseUrl));
    }
}