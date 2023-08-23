package com.example.golanceexampleservice.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SlackBotClient {
    public void notify(String message) {
        // TODO
        log.info("slack message sent: {}", message);

    }
}
