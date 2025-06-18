package dev.raniery.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String simpleChat(String message) {
        return this.chatClient.prompt()
            .user(message)
            .call()
            .content();
    }
}
