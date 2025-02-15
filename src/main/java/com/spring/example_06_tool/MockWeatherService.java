package com.spring.example_06_tool;

import org.springframework.ai.tool.annotation.Tool;

class MockWeatherService {
    @Tool(description = "Get the current temperature")
    public String forecastTemperature(String city) {
        return "30.0";
    }

}
