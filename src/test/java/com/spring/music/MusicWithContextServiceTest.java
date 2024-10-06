package com.spring.music;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicWithContextServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(MusicWithContextServiceTest.class);

    @Autowired
    private MusicWithContextService musicWithContextService;

    @Test
    void shouldFindSongsFromArtist() {
        var response = this.musicWithContextService.findAlbumsFromArtist();
        this.logger.info(response);
    }
}
