package com.spring.movie;

import java.util.List;

public record Author(String name, List<String> books) {
}
