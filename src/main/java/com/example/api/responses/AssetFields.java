package com.example.api.responses;

public record AssetFields(String Id, String Name, String SerialNumber) implements Identifiable {
    @Override
    public String getId() {
        return Id();
    }
}
