package com.spring.image;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Test
    void shouldForecastWeather() {
        var response = this.imageService.analyseWeather();
        logger.info(response);
    }

    @Test
    void shouldDescribeScientist() {
        var response = this.imageService.describeScientist();
        logger.info(response);
    }
}
