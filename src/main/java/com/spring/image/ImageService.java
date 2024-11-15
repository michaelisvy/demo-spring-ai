package com.spring.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
class ImageService {

    @Value("classpath:images/singapore-weather.png")
    private Resource imageResourceWeather;

    @Value("classpath:images/scientist.jpg")
    private Resource imageResourceScientist;

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

    public String describeScientist() {
        return this.chatClient.prompt()
                .user(
                        userSpec -> userSpec.text("can you describe this person? And what is written on top of his head?")
                                .media(MimeTypeUtils.IMAGE_PNG, this.imageResourceScientist)
                )
                .call()
                .content();
    }
}
