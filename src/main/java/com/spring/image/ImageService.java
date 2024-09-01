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

    @Value("classpath:images/mic-profile.png")
    private Resource imageResourcePhoto;

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

    public String describePerson() {
        return this.chatClient.prompt()
                .user(
                        userSpec -> userSpec.text("Can you describe this person?")
                                .media(MimeTypeUtils.IMAGE_PNG, this.imageResourcePhoto)
                )
                .call()
                .content();
    }
}
