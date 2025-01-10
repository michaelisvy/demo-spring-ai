package com.spring.example_06_function;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class WeatherAggregatorServiceTest {
    @Autowired
    private WeatherAggregatorService weatherAggregatorService;

    @Test
    public void shouldAggregateWeatherData() {
        var response = this.weatherAggregatorService.aggregateWeatherData("What's the weather like in San Francisco, Tokyo, and Paris?");
        assertThat(response).isNotNull();
    }
}
