package com.spring.book;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookSearchServiceTest {
    @Autowired
    private BookSearchService bookSearchService;

    private static final Logger logger = LoggerFactory.getLogger(BookSearchService.class);

    @Test
    void shouldAskQuestion() {
        var response = this.bookSearchService.answerQuestion("who is the murderer?");
        logger.info(response);
    }
}
