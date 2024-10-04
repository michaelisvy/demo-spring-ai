package com.spring.music;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class MusicService {

    private final ChatClient chatClient;

    public MusicService(ChatClient.Builder builder) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    public String recommendSong(String topic) {
        return this.chatClient.prompt()
                .user( userSpec -> userSpec.text("Can you recommend a song about about {topic}")
                        .param("topic", topic))
                .call()
                .content();
    }

    public Song findBestSongWithStyle(String style) {
        return this.chatClient.prompt()
                .user( userSpec -> userSpec.text("Can you recommend the best songs in the style of {style}")
                        .param("style", style))
                .call()
                .entity(Song.class);
    }

    public List<Song> findFiveBestSongsWithStyle(String style) {
        return this.chatClient.prompt()
                .user( userSpec -> userSpec.text("Can you recommend the 5 best songs in the style of {style}")
                        .param("style", style))
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }



}
