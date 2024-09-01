package com.spring.olympics;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OlympicsServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(OlympicsServiceTest.class);

    private final OlympicsService olympicsService;

    @Autowired
    public OlympicsServiceTest(OlympicsService olympicsService) {
        this.olympicsService = olympicsService;
    }

    @Test
    public void shouldFindOlympicsSports() throws IOException {
        var output = this.olympicsService.findOlympicSports();
        this.logger.info(output);
        assertThat(output).isNotEmpty();
    }
}
