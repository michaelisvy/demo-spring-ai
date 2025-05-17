package com.spring.example_03_chatMemory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
class ChatMemoryService {
    private final ChatClient chatClient;
    public ChatMemoryService(ChatClient.Builder builder, ChatModel chatModel) {
        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

        this.chatClient = ChatClient.builder(chatModel)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
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
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
