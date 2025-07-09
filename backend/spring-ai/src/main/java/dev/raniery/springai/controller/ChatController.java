package dev.raniery.springai.controller;

import dev.raniery.springai.dto.Chat;
import dev.raniery.springai.dto.ChatMemoryMessage;
import dev.raniery.springai.dto.ChatMessage;
import dev.raniery.springai.dto.ChatResponse;
import dev.raniery.springai.service.ChatService;
import dev.raniery.springai.service.MemoryChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{chatId}")
    ChatMessage simpleChatMemory(@PathVariable String chatId, @RequestBody ChatMessage message) {
        String response = memoryChatService.chat(message.message(), chatId);

        return new ChatMessage(response);
    }

    @PostMapping("/start")
    ChatResponse startNewChat(@RequestBody ChatMessage chatMessage) {
        return this.memoryChatService.createChat(chatMessage.message());
    }

    @GetMapping
    List<Chat> getAllChatsForUser() {
        return this.memoryChatService.getAllChatsForUser();
    }

    @GetMapping("/{chatId}")
    List<ChatMemoryMessage> getChatMessages(@PathVariable String chatId) {
        return this.memoryChatService.getChatMessages(chatId);
    }
}
