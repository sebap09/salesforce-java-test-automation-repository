package com.example.api.responses;

public record AccountFields(String Name, String Id, String Description, boolean IsDeleted) implements Identifiable {
    @Override
    public String getId() {
        return Id();
    }
}
