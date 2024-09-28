package com.spring.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@Service
class ChatMemoryService {
    private final ChatClient chatClient;
    public ChatMemoryService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }
    public String promptQuestionWithChatMemory(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    public String promptQuestionWithChatMemoryAndConversationId(String message, String conversationId) {
        return chatClient.prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversationId))
                .call()
                .content();
    }
}
