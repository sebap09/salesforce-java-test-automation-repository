package com.example.api.responses;

import java.util.List;

public record SObjectResponse(String id, List<Object> errors, Boolean success) {
}
