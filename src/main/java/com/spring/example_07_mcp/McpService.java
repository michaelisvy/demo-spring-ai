package com.spring.example_07_mcp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
class McpService {

    private final ChatClient chatClient;

    public McpService(ChatClient.Builder builder, FileExploringService fileExploringService) {
        this.chatClient = builder.defaultTools(fileExploringService).build();
    }

    public String promptQuestion() {
        var prompt = """
                Which files are in the user context folder?
                And out of those files, which one has a name that corresponds to a Chinese greeting?
                """;
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
