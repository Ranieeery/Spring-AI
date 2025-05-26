package dev.raniery.springai.controller;

import dev.raniery.springai.dto.ChatMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/simple-chat")
    String generation(ChatMessage message) {
        return this.chatClient.prompt()
            .user(message.message())
            .call()
            .content();
    }
}
