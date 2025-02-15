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
class MusicService {

    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);


    @Value("classpath:document/rock-albums.pdf")
    // replace it with @Value("classpath:music/rock-albums.pdf") in order to test reading from a PDF file
    private Resource rockAlbumsResource;

    @Value("classpath:document/template.st")
    private Resource template;

    private ChatClient chatClient;

    public MusicService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    private String loadDocumentText() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(this.rockAlbumsResource);
        List<Document> documents = tikaDocumentReader.read();

        return documents.stream().map(Document::getFormattedContent).collect(Collectors.joining("\n\n"));

    }

    public String findAlbumsFromArtist() {
        var text = loadDocumentText();
        logger.info(text);
        return this.chatClient.prompt()
                .user(userSpec -> userSpec.text(this.template)
                        .param("context", text)
                        .param("question", "Which are the albums from Foo Fighters?")
                )
                .call().content();
    }
}
