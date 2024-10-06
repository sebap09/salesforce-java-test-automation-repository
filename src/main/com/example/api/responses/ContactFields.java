package com.example.api.responses;

public record ContactFields(String Name, String Id, String Description, String FirstName, String LastName) implements Identifiable{
    @Override
    public String getId() {
        return Id();
    }
}
