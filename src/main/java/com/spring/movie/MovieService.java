package com.spring.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class MovieService {
    
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final ChatClient chatClient;

    public MovieService(ChatClient.Builder builder) {
        this.chatClient = builder
                            .defaultSystem("You are a helpful assistant writing in formal English")
                            .build();
    }

    public String generateResponse(String message) {
        return this.chatClient.prompt()
                .system("You are a helpful assistant writing in formal English")
                .user(message)
                .call()
                .content();
    }

    public ActorFilms findActorFilms(String message) {
        return this.chatClient.prompt()
                .user(message)
                .call()
                .entity(ActorFilms.class);
    }

    public List<ActorFilms> findActorFilmsList(String message) {
        return this.chatClient.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }




    public Flux<String> generateResponseFlux(String message) {
        return this.chatClient.prompt()
                .user(message)
                .stream().content();
    }

    public String recommendMovie(String topic) {
        return this.chatClient.prompt()
                .user(user -> user.text("Can you recommend a movie about about {topic}")
                                  .param("topic", topic))
                .call()
                .content();
    }
}
