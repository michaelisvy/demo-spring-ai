package com.spring.music;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
