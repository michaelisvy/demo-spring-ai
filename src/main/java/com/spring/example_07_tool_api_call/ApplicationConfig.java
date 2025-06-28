package com.spring.example_07_tool_api_call;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class ApplicationConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
