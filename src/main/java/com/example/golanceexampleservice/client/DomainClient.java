package com.example.golanceexampleservice.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.Optional;

@Log4j2
@Component
public class DomainClient {

    private final Integer FAILED_CODE = -1;
    public Integer check(String domain) {
        WebClient webClient = WebClient.create(domain);
        WebClient.ResponseSpec response = webClient
                .get()
                .retrieve();

        Optional<Integer> status = Optional.empty();
        try {
            status = Optional.ofNullable(response.toBodilessEntity().block())
                    .map(ResponseEntity::getStatusCode)
                    .map(HttpStatusCode::value);
        } catch (WebClientRequestException e) {
            log.error("Exception trying to reach: {} ", domain, e);
        }
        Integer statusCode = status.orElse(FAILED_CODE);
        log.info("domain checked: {}, status: {}", domain, statusCode);
        return statusCode;
    }
}
