# Demo Application for Spring AI

## Setup
* When using OpenAI, setup your API key with this environment variable: OPENAI_API_KEY
* See [application.properties](src/main/resources/application.properties) for more details

## Calls to the ChatClient (simple call, parameters, entities)
* See [MusicService](src/main/java/com/spring/music/MusicService.java) and [MusicServiceTest](src/test/java/com/spring/music/MusicServiceTest.java)

## usage of ChatMemory
* See [ChatMemory](src/main/java/com/spring/memory/ChatMemoryService.java) and [ChatMemoryTest](src/test/java/com/spring/memory/ChatMemoryServiceTest.java)

## Process an image
* See [ImageService](src/main/java/com/spring/image/ImageService.java) and [ImageServiceTest](src/test/java/com/spring/image/ImageServiceTest.java)

## Retrieval Augmented Generation with Excel and PDF files
* See [MusicWithContextService](src/main/java/com/spring/music/MusicWithContextService.java) and [MusicWithContextServiceTest](src/test/java/com/spring/music/MusicWithContextServiceTest.java)


