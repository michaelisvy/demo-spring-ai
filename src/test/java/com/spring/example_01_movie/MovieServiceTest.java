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
        var input = "Generate the most popular movie staring Bruce Willis";
        Movie movie = this.movieService.findMovie(input);
        assertThat(movie).isNotNull();
        assertThat(movie.title()).isNotEmpty();
        assertThat(movie.actors()).isNotEmpty();
        this.logger.info(movie.title());
        this.logger.info(movie.actors().toString());
    }

    @Test
    public void shouldUseEntityList() {
        var input = "Generate the 10 most popular movies starring Bruce Willis";
        var actorFilmsList = this.movieService.findMovieList(input);
        assertThat(actorFilmsList).hasSize(10);
        this.logger.info(actorFilmsList.toString());
    }
}
