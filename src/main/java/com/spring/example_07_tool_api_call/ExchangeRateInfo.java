package com.spring.example_07_tool_api_call;

import java.util.Map;

public record ExchangeRateInfo(
        double amount,
        String base,
        String date,
        Map<String, Double> rates) {}
