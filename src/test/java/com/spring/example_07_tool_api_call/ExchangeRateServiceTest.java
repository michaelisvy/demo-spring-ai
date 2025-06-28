package com.spring.example_07_tool_api_call;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ExchangeRateServiceTest {

    private final ChatClient chatClient;

    private final RestClient restClient;

    @Autowired
    public ExchangeRateServiceTest(RestClient restClient, ChatClient.Builder chatClientBuilder, ExchangeRateTools exchangeRateTools) {
        this.restClient = restClient;

        this.chatClient = chatClientBuilder
                            .defaultAdvisors(new SimpleLoggerAdvisor())
                            .defaultTools(exchangeRateTools)
                            .build();
    }

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateServiceTest.class);
    

    @Test
    void shouldGetExchangeRate() {
        var response = this.chatClient.prompt()
                .user( "I have 1000 Euros and I am in London for one week. Would that be enough for a 3-star hotel in the city and food expenses?" )
                .call()
                .content();
        assertThat(response).isNotEmpty();


    }
}
