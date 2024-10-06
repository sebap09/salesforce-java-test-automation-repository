package com.example.api.responses;

public record UserFields(String Id) implements Identifiable {
    @Override
    public String getId() {
        return Id();
    }
}
