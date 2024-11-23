package com.spring.example_04_RAG;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class RichDocumentService {

    private static final Logger logger = LoggerFactory.getLogger(RichDocumentService.class);


    @Value("classpath:document/slides-sample-diagrams.pdf")
    private Resource documentResource;

    @Value("classpath:document/template.st")
    private Resource template;

    private ChatClient chatClient;

    public RichDocumentService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    private String loadDocumentText() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(this.documentResource);
        List<Document> documents = tikaDocumentReader.read();

        return documents.stream().map(Document::getContent).collect(Collectors.joining("\n\n"));

    }

    public String findData() {
        var text = loadDocumentText();
        logger.info(text);
        return this.chatClient.prompt()
                .user(userSpec -> userSpec.text(this.template)
                        .param("context", text)
                        //.param("question", "Based on the third slide, is StudentDTO a Spring component?")
                        .param("question", "Based on the fourth slide, what is the relationship between OracleStudentRepository and StudentRepository?")
                        //.param("question", "Based on the first slide, which are the Spring projects by the community?")
                )
                .call().content();
    }
}
