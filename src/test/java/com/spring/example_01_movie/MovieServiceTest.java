package com.spring.example_01_movie;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MovieServiceTest {
    private final MovieService movieService;
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceTest.class);

    @Autowired
    public MovieServiceTest(MovieService movieService) {
        this.movieService = movieService;
    }

    @Test
    public void shouldMakeSimpleCall() {
        var input = "What are the 10 best movies of all time?";
        var output = this.movieService.generateSimpleResponse(input);
        this.logger.info(input);
        this.logger.info(output);
        assertThat(output).isNotEmpty();
    }

    @Test
    public void shouldUseParameters() {
        var response = this.movieService.recommendMovie("computers");
        this.logger.info(response);
        assertThat(response).isNotEmpty();
    }

    @Test
    public void shouldUseEntity() {
        var input = "Generate the 10 most popular movies staring Bruce Willis";
        var actorFilms = this.movieService.findActorFilms(input);
        assertThat(actorFilms).isNotNull();
        assertThat(actorFilms.movies()).isNotEmpty();
        this.logger.info(actorFilms.actor());
        this.logger.info(actorFilms.movies().toString());
    }

    @Test
    public void shouldUseEntityList() {
        var input = "Generate the 10 most popular movies for Eddie Murphy and Bruce Willis";
        var actorFilmsList = this.movieService.findActorFilmsList(input);
        assertThat(actorFilmsList).hasSize(2);
        this.logger.info(actorFilmsList.toString());
    }
}
