package com.spring.example_06_vector;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
class BookService {
    private final ChatClient chatClient;

    public BookService(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore)).build();
    }

    public String answerQuestion(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
