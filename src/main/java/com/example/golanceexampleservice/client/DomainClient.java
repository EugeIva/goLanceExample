package com.example.golanceexampleservice.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DomainClient {
    public Integer check(String domain) {
        // todo
        log.info("domain checked: {}", domain);
        return 520;
    }
}
