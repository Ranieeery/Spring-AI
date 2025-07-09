package dev.raniery.springai.service;

import dev.raniery.springai.repository.MemoryChatRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.stereotype.Service;

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

    public String chat(String message, String chatId) {
        return this.chatClient.prompt()
            .advisors( a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
            .user(message)
            .call()
            .content();
    }

    public record NewChatResponse(String chatId, String description, String response) {}

    public NewChatResponse createChat(String message) {
        String description = generateDescription(message);
        String chatId = this.memoryChatRepository.generateChatId(USER_ID, description);
        String response = this.chat(description, chatId);

        return new NewChatResponse(chatId, description, response);
    }

    private String generateDescription(String message) {
        return this.chatClient.prompt()
            .user(DESCRIPTION_PROMPT + message)
            .call()
            .content();
    }
}
