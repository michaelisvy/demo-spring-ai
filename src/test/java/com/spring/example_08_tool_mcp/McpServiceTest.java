package com.spring.example_08_tool_mcp;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class McpServiceTest {
    @Autowired
    private McpService mcpService;

    private static final Logger logger = LoggerFactory.getLogger(McpServiceTest.class);


    @Test
    public void shouldPromptQuestion() {
        var response = this.mcpService.promptQuestion();
        logger.info(response);
        assertThat(response).contains("ni hao");
    }
}
