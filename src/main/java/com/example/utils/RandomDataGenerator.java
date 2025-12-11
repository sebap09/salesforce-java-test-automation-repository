package com.example.utils;

import net.datafaker.Faker;
import org.json.JSONObject;

public class RandomDataGenerator {

    private final Faker faker;

    public RandomDataGenerator() {
        this.faker = new Faker();
    }


    public JSONObject generateAccountData(JSONObject defaultData){
        JSONObject accountData = new JSONObject(defaultData.toString());
        accountData.put("Name",faker.company().name());
        accountData.put("Phone",faker.phoneNumber().phoneNumber());
        accountData.put("BillingPostalCode",faker.address().postcode());
        accountData.put("BillingCity",faker.address().city());
        accountData.put("BillingState",faker.address().state());
        accountData.put("BillingCountry",faker.address().country());
        accountData.put("BillingStreet",faker.address().streetAddress(false));
        accountData.put("Description","Test Automation Description " + faker.regexify("[a-zA-Z0-9]{4}"));

        return accountData;
    }

    public JSONObject generateContactData(JSONObject defaultData){
        JSONObject contactData = new JSONObject(defaultData.toString());
        contactData.put("FirstName",faker.name().firstName());
        contactData.put("LastName",faker.name().lastName());
        contactData.put("Email",faker.internet().emailAddress());
        contactData.put("MobilePhone",faker.phoneNumber().phoneNumber());

        return contactData;
    }

    public JSONObject generateAssetData(JSONObject defaultData){
        JSONObject assetData = new JSONObject(defaultData.toString());
        assetData.put("Name",faker.brand().sport());
        assetData.put("SerialNumber",faker.number().digits(12));
        assetData.put("Description","Test Automation Description " + faker.regexify("[a-zA-Z0-9]{4}"));
        assetData.put("IsCompetitorProduct",faker.bool().bool());

        return assetData;
    }

    public JSONObject generateProductData(JSONObject defaultData){
        JSONObject productData = new JSONObject(defaultData.toString());
        productData.put("Name",faker.brand().watch());
        productData.put("ProductCode",faker.number().digits(12));
        productData.put("Description","Test Automation Description " + faker.regexify("[a-zA-Z0-9]{4}"));

        return productData;
    }
}
