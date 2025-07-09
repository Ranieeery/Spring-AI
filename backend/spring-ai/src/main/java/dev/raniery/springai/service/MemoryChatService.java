package dev.raniery.springai.service;

import dev.raniery.springai.dto.Chat;
import dev.raniery.springai.dto.ChatMemoryMessage;
import dev.raniery.springai.dto.ChatResponse;
import dev.raniery.springai.repository.MemoryChatRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryChatService {

    private final ChatClient chatClient;

    private final MemoryChatRepository memoryChatRepository;

    private static final String USER_ID = "raniery";
    private static final String DESCRIPTION_PROMPT = "Generate a chat description for the following message, limiting it to 30 characters: ";

    public MemoryChatService(ChatClient.Builder chatClientBuilder, JdbcChatMemoryRepository jdbcChatMemoryRepository, MemoryChatRepository memoryChatRepository) {
        this.memoryChatRepository = memoryChatRepository;

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
            .chatMemoryRepository(jdbcChatMemoryRepository)
            .maxMessages(10)
            .build();

        this.chatClient = chatClientBuilder
            .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build(),
                new SimpleLoggerAdvisor())
            .build();
    }

    //TODO: chatId validation
    public String chat(String message, String chatId) {
        return this.chatClient.prompt()
            .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
            .user(message)
            .call()
            .content();
    }

    public ChatResponse createChat(String message) {
        String description = generateDescription(message);
        String chatId = this.memoryChatRepository.generateChatId(USER_ID, description);
        String response = this.chat(description, chatId);

        return new ChatResponse(chatId, description, response);
    }

    private String generateDescription(String message) {
        return this.chatClient.prompt()
            .user(DESCRIPTION_PROMPT + message)
            .call()
            .content();
    }

    public List<Chat> getAllChatsForUser() {
        return this.memoryChatRepository.getAllChatsForUser(USER_ID);
    }

    public List<ChatMemoryMessage> getChatMessages(String chatId) {
        return this.memoryChatRepository.getChatMessages(chatId);
    }
}
