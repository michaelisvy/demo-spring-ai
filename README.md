# Demo Application for Spring AI

## Setup
* When using OpenAI, setup your API key with this environment variable: OPENAI_API_KEY
* The OpenAI cost for running this project with the default model should be a few cents at most. 
* See [application.properties](src/main/resources/application.properties) for more details

## Calls to the ChatClient (simple call, parameters, entities)
* See [MusicService](src/main/java/com/spring/music/MusicService.java) and [MusicServiceTest](src/test/java/com/spring/music/MusicServiceTest.java)

## usage of ChatMemory
* See [ChatMemory](src/main/java/com/spring/memory/ChatMemoryService.java) and [ChatMemoryTest](src/test/java/com/spring/memory/ChatMemoryServiceTest.java)

## Use an Image as Input and ask questions about the image
* See [ImageService](src/main/java/com/spring/image/ImageService.java) and [ImageServiceTest](src/test/java/com/spring/image/ImageServiceTest.java)

## Retrieval Augmented Generation with Excel and PDF files
* See [MusicWithContextService](src/main/java/com/spring/music/MusicWithContextService.java) and [MusicWithContextServiceTest](src/test/java/com/spring/music/MusicWithContextServiceTest.java)

## Using an in-memory Vector Database
* See [BookSearchService](src/main/java/com/spring/book/BookSearchService.java) and [BookSearchServiceTest](src/test/java/com/spring/book/BookSearchServiceTest.java)



