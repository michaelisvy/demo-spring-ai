package com.spring.example_05_vector;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
class BookService {
    private final ChatClient chatClient;

    public BookService(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor(),  QuestionAnswerAdvisor.builder(vectorStore).build()).build();
    }

    public String answerQuestion(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
