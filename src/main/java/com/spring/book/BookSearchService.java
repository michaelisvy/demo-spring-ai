package com.spring.book;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
class BookSearchService {
    private final ChatClient chatClient;

    public BookSearchService(ChatClient vectorStoreChatClient) {
        this.chatClient = vectorStoreChatClient;
    }

    public String answerQuestion(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
