package com.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * This class has been imported from Craig Wall's LoggingInterceptor: https://github.com/habuma/logging-interceptor
 * # It provides better logging indentation compared to the default Spring AI Logging advisor (called SimpleLoggerAdvisor)
 * # Will be removed once this issue will be fixed: https://github.com/spring-projects/spring-ai/issues/1364
 */
@Configuration
public class HttpLoggingConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingConfiguration.class);

    @Bean
    RestClient.Builder restClientBuilder() {
        ClientHttpRequestInterceptor interceptor = loggingInterceptor();
        return RestClient.builder().requestInterceptor(interceptor);
    }

    private ClientHttpRequestInterceptor loggingInterceptor() {
        ClientHttpRequestInterceptor interceptor = (request, requestBody, ex) -> {
            ClientHttpResponse response = ex.execute(request, requestBody);
            if (logger.isDebugEnabled()) {
                logger.debug("Request body: \n{}\n", new String(requestBody, StandardCharsets.UTF_8));

                final ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
                String body = getBodyFromResponse(responseCopy);

                logger.debug("Response body:\n{}\n", body);

                return responseCopy;
            } else {
                return response;
            }
        };
        return interceptor;
    }

    private String getBodyFromResponse(ClientHttpResponse responseCopy) throws IOException {
        InputStreamReader reader = new InputStreamReader(responseCopy.getBody(), StandardCharsets.UTF_8);
        String body = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
        return body;
    }

    final static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse response;
        @Nullable
        private byte[] body;

        BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return this.response.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.getBody());
            }
            return new ByteArrayInputStream(this.body);
        }

        @Override
        public void close() {
            this.response.close();
        }
    }
}
