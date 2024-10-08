package com.spring.book;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
class BookSearchService {
    private final ChatClient chatClient;

    public BookSearchService(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore)).build();
    }

    public String answerQuestion(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
