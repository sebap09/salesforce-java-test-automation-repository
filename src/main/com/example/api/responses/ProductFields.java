package com.example.api.responses;

public record ProductFields(String Name, String Id, String Description, boolean IsDeleted, boolean IsActive, String ProductCode) implements Identifiable{
    @Override
    public String getId() {
        return Id();
    }
}
