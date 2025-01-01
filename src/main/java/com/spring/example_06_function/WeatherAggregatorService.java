package com.spring.example_06_function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;

@Service
class WeatherAggregatorService {
    private final ChatClient chatClient;

    private static final Logger logger = LoggerFactory.getLogger(WeatherAggregatorService.class);


    public WeatherAggregatorService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a helpful assistant writing in formal English")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    public String aggregateWeatherData(String input) {
        var response = this.chatClient.prompt("What's the weather like in San Francisco, Tokyo, and Paris?")
                .functions("currentWeather") // Enable the function
                .call().
                content();

        logger.info("Response: {}", response);
        return response;
    }
}
