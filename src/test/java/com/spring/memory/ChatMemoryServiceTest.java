package com.spring.memory;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatMemoryServiceTest {
    @Autowired
    private ChatMemoryService chatMemoryService;

    private static final Logger logger = LoggerFactory.getLogger(ChatMemoryServiceTest.class);


    @Test
    void shouldUseChatMemory() {
        var response = this.chatMemoryService.promptQuestionWithChatMemory("which are the 5 best french movies since 2020?");
        logger.info(response);
        response = this.chatMemoryService.promptQuestionWithChatMemory("among those 5, which one was the most popular?");
        logger.info(response);
    }

    @Test
    void shouldUseChatMemoryWithConversationId() {
        var response = this.chatMemoryService.promptQuestionWithChatMemoryAndConversationId("which are the 5 best french movies since 2020?", "001");
        logger.info(response);
        response = this.chatMemoryService.promptQuestionWithChatMemoryAndConversationId("which are the 2 best Chinese songs since 2020?", "002");
        logger.info(response);
        response = this.chatMemoryService.promptQuestionWithChatMemoryAndConversationId("among those 5, which one was the most popular?", "001");
        logger.info(response);
    }
}
