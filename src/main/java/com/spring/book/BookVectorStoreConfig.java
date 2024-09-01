package com.spring.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
class BookVectorStoreConfig {

    public static final int NUMBER_OF_TOKENS_PER_VECTOR = 350;
    private final String VECTOR_STORE_FILE = "vectorstore.json";
    @Value("classpath:book/crime-in-paris.txt")
    private Resource bookContent;

    private static final Logger logger = LoggerFactory.getLogger(BookVectorStoreConfig.class);


    @Bean
    ChatClient vectorStoreChatClient(ChatClient.Builder builder , VectorStore vectorStore) {
        return builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults().withTopK(1)))
                .build();
    }
    @Bean
    VectorStore simpleVectorStore(EmbeddingModel embeddingModel) throws IOException {
        logger.info("Embedding Model: {}", embeddingModel.toString());
        var simpleVectorStore = new SimpleVectorStore(embeddingModel);
        var vectorStoreFile = getVectorStoreFile();
        if (vectorStoreFile.exists()) {
            logger.info("Vector Store File Exists,");
            simpleVectorStore.load(vectorStoreFile);
        } else {
            logger.info("Vector Store File Does Not Exist, loading documents");
            List<Document> splitDocuments = buildSplitDocuments(this.bookContent);
            simpleVectorStore.add(splitDocuments);
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }

    private List<Document> buildSplitDocuments(Resource documentResource) {
        TextReader textReader = new TextReader(documentResource);
        textReader.getCustomMetadata().put("filename",documentResource.getFilename());
        List<Document> documents = textReader.get();
        TextSplitter textSplitter = buildTokenTextSplitter(NUMBER_OF_TOKENS_PER_VECTOR);
        List<Document> splitDocuments = textSplitter.apply(documents);
        return splitDocuments;
    }

    private TokenTextSplitter buildTokenTextSplitter(int numberOfTokensPerVector) {
        return new TokenTextSplitter(numberOfTokensPerVector, 350, 5, 10000, true);
    }

    private File getVectorStoreFile() {
        Path path = Paths.get( "src", "main", "resources");
        String absolutePath = path.toFile().getAbsolutePath() + "/" + VECTOR_STORE_FILE;
        return new File(absolutePath);
    }
}
