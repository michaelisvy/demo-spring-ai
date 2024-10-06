package com.spring.music;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class MusicWithContextService {

    @Value("classpath:music/rock-albums.xlsx")
    // replace it with @Value("classpath:music/rock-albums.pdf") in order to test reading from a PDF file
    private Resource rockAlbumsResource;

    @Value("classpath:music/template.st")
    private Resource template;

    private ChatClient chatClient;

    public MusicWithContextService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    private String loadDocumentText() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(this.rockAlbumsResource);
        List<Document> documents = tikaDocumentReader.read();

        return documents.stream().map(Document::getContent).collect(Collectors.joining("\n\n"));

    }

    public String findAlbumsFromArtist() {
        return this.chatClient.prompt()
                .user(userSpec -> userSpec.text(this.template)
                        .param("context", loadDocumentText())
                        .param("question", "Which are the albums from Foo Fighters?")
                )
                .call().content();
    }
}
