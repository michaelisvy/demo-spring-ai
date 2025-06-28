package com.spring.example_07_tool_api_call;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
class ExchangeRateTools {
    private final RestClient restClient;

    public ExchangeRateTools(RestClient restClient) {
        this.restClient = restClient;
    }

    @Tool(description = "A service that retrieves the exchange rate between two currencies")
    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        String apiUrl = String.format("https://api.frankfurter.dev/v1/latest?base=%s&symbols=%s", baseCurrency, targetCurrency);
        var exchangeRateInfo = restClient
                .get()
                .uri(apiUrl)
                .retrieve()
                .body(ExchangeRateInfo.class);

        return exchangeRateInfo.rates().get(targetCurrency);
    }
}
