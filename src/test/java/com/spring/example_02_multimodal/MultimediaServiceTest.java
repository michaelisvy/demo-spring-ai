package com.spring.example_02_multimodal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
class MultimediaServiceTest {
    @Autowired
    private MultimediaService multimediaService;

    private static final Logger logger = LoggerFactory.getLogger(MultimediaService.class);

    @Test
    void shouldForecastWeather() {
        var response = this.multimediaService.analyseWeather();
        logger.info(response);
    }

    @Test
    void shouldTranscribeFromSpeechToText() {
        var response = this.multimediaService.transcribeFromSpeechToText();
        assertThat(response).isEqualTo("here comes the sun");
        logger.info(response);
    }
}
