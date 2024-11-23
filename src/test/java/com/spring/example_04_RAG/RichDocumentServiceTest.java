package com.spring.example_04_RAG;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RichDocumentServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(RichDocumentServiceTest.class);

    @Autowired
    private RichDocumentService richDocumentService;

    @Test
    void shouldFindData() {
        var response = this.richDocumentService.findData();
        this.logger.info(response);
    }
}
