package com.spring.example_07_mcp;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Duration;

@Service
class McpService {

    private final ChatClient chatClient;

    public McpService(@Qualifier("chatClientWithMcp") ChatClient chatClient) {
        this.chatClient = chatClient;
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

/**
 * Loads a file server using Node.js and the Model Context Protocol.
 * Allows the LLM to access one folder inside your filesystem
 */
@Configuration
class McpConfig {
    @Bean ChatClient chatClientWithMcp(ChatClient.Builder builder, McpSyncClient mcpSyncClient) {
        var tools = new SyncMcpToolCallbackProvider(mcpSyncClient);
        return builder.defaultAdvisors(new SimpleLoggerAdvisor()).defaultToolCallbacks(tools).build();
    }

    @Bean
    McpSyncClient mcpSyncClient(@Value("classpath:example_07_mcp") File root) {
        var stdioParameters = ServerParameters
                .builder("npx")
                .args("-y", "@modelcontextprotocol/server-filesystem", root.getAbsolutePath())
                .build();
        var mcp = McpClient.sync(new StdioClientTransport(stdioParameters))
                .requestTimeout(Duration.ofMinutes(1))
                .build();
        mcp.initialize();
        return mcp;
    }
}
