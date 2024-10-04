package com.spring.music;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MusicServiceTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MusicServiceTest.class);
    
    @Autowired
    private MusicService musicService;

    @Test
    void shouldRecommendSong() {
        var response = this.musicService.recommendSong("Paris");
        this.logger.info(response);
    }


    @Test
    void shouldFindBestSongWithStyle() {
        var response = this.musicService.findBestSongWithStyle("Folk guitar");
        this.logger.info(response.toString());
    }

    @Test
    void shouldFindFiveBestSongsWithStyle() {
        var response = this.musicService.findFiveBestSongsWithStyle("Folk guitar");
        this.logger.info(response.toString());
        assertThat(response.size()).isEqualTo(5);
    }
}
