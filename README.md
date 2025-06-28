# Demo Application for Spring AI

## Setup
* When using OpenAI, setup your API key with this environment variable: OPENAI_API_KEY
* The OpenAI cost for running this project with the default model should be a few cents at most. 
* See [application.properties](src/main/resources/application.properties) for more details

## Calls to the ChatClient (simple call, parameters, entities)
* See [MovieService](src/main/java/com/spring/example_01_movie/MovieService.java) and [MusicServiceTest](src/test/java/com/spring/example_01_movie/MovieServiceTest.java)

## Use an Image as Input and ask questions about the image
* See [ImageService](src/main/java/com/spring/example_02_multimodal/ImageService.java) and [ImageServiceTest](src/test/java/com/spring/example_02_multimodal/ImageServiceTest.java)

## usage of ChatMemory
* See [ChatMemory](src/main/java/com/spring/example_03_chatMemory/ChatMemoryService.java) and [ChatMemoryTest](src/test/java/com/spring/example_03_chatMemory/ChatMemoryServiceTest.java)

## Retrieval Augmented Generation with Excel and PDF files
* See [MusicWithContextService](src/main/java/com/spring/example_04_RAG/MusicService.java) and [MusicWithContextServiceTest](src/test/java/com/spring/example_04_RAG/MusicServiceTest.java)

## Retrieval Augmented Generation with a PDF file that contains mixed text and images content
* See [MusicWithContextService](src/main/java/com/spring/example_04_RAG/RichDocumentService.java) and [MusicWithContextServiceTest](src/test/java/com/spring/example_04_RAG/RichDocumentServiceTest.java)

## Using an in-memory Vector Database
* See [BookService](src/main/java/com/spring/example_05_vector/BookService.java) and [BookServiceTest](src/test/java/com/spring/example_05_vector/BookServiceTest.java)

## usage of Tools (agents / functions) with mock tool
* See [WeatherAggregatorService](src/main/java/com/spring/example_06_tool_mock/WeatherAggregatorService.java) and [WeatherAggregatorServiceTest](src/test/java/com/spring/example_06_tool_mock/WeatherAggregatorServiceTest.java)
* 
## usage of Tools (agents / functions) with a real tool calling an API
* See [ExchangeRateTools](src/main/java/com/spring/example_07_tool_api_call/ExchangeRateTools.java) and [WeatherAggregatorServiceTest](src/test/java/com/spring/example_07_tool_api_call/ExchangeRateToolsTest.java)

## usage of MCP to read file names from the file system
* See [WeatherAggregatorService](src/main/java/com/spring/example_08_tool_mcp/McpService.java)  and [WeatherAggregatorServiceTest](src/test/java/com/spring/example_08_tool_mcp/McpServiceTest.java)


## Using a local model
* Install Ollama and run it locally
* Inside [application.properties](src/main/resources/application.properties), configure the model that you are running inside Ollama (default is `spring.ai.ollama.chat.model=llama3.2`)
* Inside [pom.xml](src/main/resources/pom.xml), change the dependency `spring-ai-openai-spring-boot-starter` into `spring-ai-ollama-spring-boot-starter`
* Run all the tests (some will not be working as some of the features are not supported by local models)



