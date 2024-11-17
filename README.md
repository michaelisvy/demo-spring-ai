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
* See [BookService](src/main/java/com/spring/vector/BookService.java) and [BookServiceTest](src/test/java/com/spring/book/BookServiceTest.java)

## Using a local model
* Install Ollama and run it locally
* Inside [application.properties](src/main/resources/application.properties), configure the model that you are running inside Ollama (default is `spring.ai.ollama.chat.model=llama3.2`)
* Inside [pom.xml](src/main/resources/pom.xml), change the dependency `spring-ai-openai-spring-boot-starter` into `spring-ai-ollama-spring-boot-starter`
* Run all the tests (some will not be working as some of the features are not supported by local models)



