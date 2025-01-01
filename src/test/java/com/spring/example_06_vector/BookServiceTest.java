package com.spring.example_06_vector;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Test
    void shouldAskQuestion() {
        var response = this.bookService.answerQuestion("who attempted to kill Henri?");
        logger.info(response);
    }
}
