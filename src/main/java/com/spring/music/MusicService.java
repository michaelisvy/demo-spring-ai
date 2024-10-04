package com.spring.music;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
class MusicService {

    private final ChatClient chatClient;

    public MusicService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String recommendSong(String topic) {
        return this.chatClient.prompt()
                .user( userSpec -> userSpec.text("Can you recommend a song about about {topic}")
                        .param("topic", topic))
                .call()
                .content();
    }

}
