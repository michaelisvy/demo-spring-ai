package com.spring.example_08_tool_mcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
class FileExploringService {
    private static final Logger logger = LoggerFactory.getLogger(FileExploringService.class);


    @Tool(description = "returns all files in a folder called example_07_mcp")
    private List<String> listFiles() {
        Path parentFolderPath = Paths.get("src", "main", "resources", "example_07_mcp");
        File parentFolderDirectory = parentFolderPath.toFile();
        List<String> fileNames = Arrays.stream(Objects.requireNonNull(parentFolderDirectory.listFiles()))
                .map(File::getName)
                .toList();
        logger.info("Files in the folder: {}", fileNames);
        return fileNames;
    }
}
