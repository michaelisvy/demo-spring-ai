package com.spring.example_06_function;

import java.util.function.Function;

class MockWeatherService implements Function<MockWeatherService.Request, MockWeatherService.Response> {
    @Override
    public Response apply(Request request) {
        return new Response(30.0);
    }

    record Request(String location) {}
    record Response(double temperature) {}
}
