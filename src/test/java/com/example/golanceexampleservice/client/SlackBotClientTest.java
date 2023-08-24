package com.example.golanceexampleservice.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.golanceexampleservice.config.SlackProperties;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.methods.SlackApiException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

public class SlackBotClientTest {

    @InjectMocks
    private SlackBotClient slackBotClient;
    @Mock
    private SlackProperties slackProperties;
    @Mock
    private Slack slack;
    @Mock
    private MethodsClient methodsClient;
    @Mock
    private ChatPostMessageResponse chatPostMessageResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MockedStatic<Slack> slackMockedStatic  = mockStatic(Slack.class);
        slackMockedStatic.when(Slack::getInstance).thenReturn(slack);
        slackBotClient.init();
    }

    @Test
    public void testNotify_success() throws IOException, SlackApiException {
        String message = "Test Message";
        String token = "TestToken";

        when(slackProperties.getToken()).thenReturn(token);
        when(slack.methods(token)).thenReturn(methodsClient);
        when(methodsClient.chatPostMessage(any(ChatPostMessageRequest.class))).thenReturn(chatPostMessageResponse);
        when(chatPostMessageResponse.isOk()).thenReturn(true);

        slackBotClient.notify(message);

        verify(slack.methods(token), times(1)).chatPostMessage(any(ChatPostMessageRequest.class));
    }

}
