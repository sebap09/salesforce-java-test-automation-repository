package com.example.api.responses;

import java.util.List;

public record SOQLResponse(int totalSize, String done, List<Object> records) {
}
