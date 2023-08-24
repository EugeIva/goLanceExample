package com.example.golanceexampleservice.client;

import com.example.golanceexampleservice.config.SlackProperties;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Log4j2
@Component
@RequiredArgsConstructor
public class SlackBotClient {

    private final SlackProperties slackProperties;
    private Slack slack;
    @PostConstruct
    public void init() {
        slack = Slack.getInstance();
    }
    public void notify(String message) {
        // TODO
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .text(message)
                .build();
        try {
            ChatPostMessageResponse response = slack.methods(slackProperties.getToken()).chatPostMessage(request);
            if (response.isOk()) {
                log.info("slack message sent: {}", message);
            } else {
                throw new IOException(response.getError());
            }
        } catch (IOException | SlackApiException e) {
            log.error("Slack bot error", e);
        }


    }
}
