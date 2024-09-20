package com.spring.olympics;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class OlympicsService {

    @Value("classpath:/olympics/olympic-sports-template.st")
    private Resource queryTemplate;

    @Value("classpath:/olympics/olympic-sports.txt")
    private Resource olympicsSportsResource;

    private final ChatClient chatClient;

    public OlympicsService(ChatClient.Builder builder) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    public String findOlympicSports() throws IOException {

        String sports = olympicsSportsResource.getContentAsString(Charset.defaultCharset());
        return this.chatClient.prompt()
                .user(
                        userSpec -> userSpec.text(this.queryTemplate)
                                            .param("context", sports)
                                            .param("question","How many sports are being included in the 2024 Summer Olympics that start with the letter 'A'?")
                )
                .call()
                .content();
    }
}
