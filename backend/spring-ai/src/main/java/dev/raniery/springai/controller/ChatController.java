package dev.raniery.springai.controller;

import dev.raniery.springai.dto.ChatMessage;
import dev.raniery.springai.service.ChatService;
import dev.raniery.springai.service.MemoryChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
class ChatController {

    private final ChatService chatService;
    private final MemoryChatService memoryChatService;

    ChatController(ChatService chatService, MemoryChatService memoryChatService) {
        this.chatService = chatService;
        this.memoryChatService = memoryChatService;
    }

    @PostMapping("/simple-chat")
    ChatMessage simpleChat(@RequestBody ChatMessage message) {
        String response = chatService.simpleChat(message.message());

        return new ChatMessage(response);
    }

//    @PostMapping("/chat-memory")
//    ChatMessage simpleChatMemory(@RequestBody ChatMessage message) {
//        String response = memoryChatService.chat(message.message());
//
//        return new ChatMessage(response);
//    }

    @PostMapping("/start")
    MemoryChatService.NewChatResponse startNewChat(@RequestBody ChatMessage chatMessage) {
        return this.memoryChatService.createChat(chatMessage.message());
    }
}
