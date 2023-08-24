package com.example.golanceexampleservice.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Log4j2
@Component
public class DomainClient {

    public Integer check(String domain) {
        WebClient webClient = WebClient.create(domain);
        Integer statusCode = webClient.get()
                .exchange()
                .flatMap(clientResponse -> {
                    int code = clientResponse.statusCode().value();
                    return Mono.just(code);
                })
                .block();

        log.info("domain checked: {}, status: {}", domain, statusCode);
        return statusCode;
    }
}
