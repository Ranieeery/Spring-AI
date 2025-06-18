package dev.raniery.springai.controller;

import dev.raniery.springai.dto.ChatMessage;
import dev.raniery.springai.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
class ChatController {

    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/simple-chat")
    ChatMessage simpleChat(@RequestBody ChatMessage message) {
        String response = chatService.simpleChat(message.message());

        return new ChatMessage(response);
    }
}
