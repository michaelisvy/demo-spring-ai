package com.spring.example_02_multimodal;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
class ImageService {

    @Value("classpath:images/singapore-weather.png")
    private Resource imageResourceWeather;

    private final ChatClient chatClient;

    public ImageService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String analyseWeather() {
        return this.chatClient.prompt()
                .user(
                        userSpec -> userSpec.text("what will be the weather like on Tuesday")
                                            .media(MimeTypeUtils.IMAGE_PNG, this.imageResourceWeather)
                )
                .call()
                .content();
    }
}
