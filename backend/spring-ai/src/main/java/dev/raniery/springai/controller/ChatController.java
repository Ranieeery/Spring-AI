package dev.raniery.springai.controller;

import dev.raniery.springai.dto.ChatMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    ChatMessage simpleChat(@RequestBody ChatMessage message) {
        String response = this.chatClient.prompt()
            .user(message.message())
            .call()
            .content();
        return new ChatMessage(response);
    }
}
