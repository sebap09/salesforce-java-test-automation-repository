package com.example.api.responses;

public record AuthenticationResponse(String access_token, String signature, String instance_url, String id, String token_type, String issued_at) {
}
