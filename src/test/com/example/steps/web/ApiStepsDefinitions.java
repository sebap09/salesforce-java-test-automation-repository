package com.example.steps.web;

import com.example.api.responses.*;
import com.example.sharedstate.SharedDataKeys;
import com.example.sharedstate.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiStepsDefinitions {
    private final TestContext testContext;
    public ApiStepsDefinitions(){
        this.testContext = TestContext.getInstance();
    }


    @ParameterType("Account|Product2|Contact|User|Asset")
    public String salesforceObject(String salesforceObject){
        return salesforceObject;
    }


    @When("I fetch Id from \"{salesforceObject}\" by {string} Name")
    public void fetchIdFromSalesforceObjectByName(String salesforceObject, String name) {
        String query = "SELECT Id from " + salesforceObject + " WHERE Name = '" + name + "'";

        Map<String,Class<? extends Identifiable>> typeMap = Map.of(
                "Account", AccountFields.class,
                "Product2", ProductFields.class,
                "Contact", ContactFields.class,
                "Asset", AssetFields.class,
                "User", UserFields.class
        );

        String id = testContext.getHttpClient().fetchData(
                testContext.getJSON().getString("environment"),
                testContext.getJSON().getJSONObject("apiData").getString("queryEndpoint"),
                testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                query,
                typeMap.get(salesforceObject)).get(0).getId();

        switch (salesforceObject){
            case "Account":
                testContext.getSharedData().setData(SharedDataKeys.ACCOUNT_NAME,name);
                testContext.getSharedData().setData(SharedDataKeys.ACCOUNT_ID,id);
                break;
            case "Product2":
                testContext.getSharedData().setData(SharedDataKeys.PRODUCT_NAME,name);
                testContext.getSharedData().setData(SharedDataKeys.PRODUCT_ID,id);
                break;
            case "Contact":
                testContext.getSharedData().setData(SharedDataKeys.CONTACT_FULL_NAME,name);
                testContext.getSharedData().setData(SharedDataKeys.CONTACT_ID,id);
                break;
        }

        testContext.getSharedData().setData(SharedDataKeys.REFERENCE_ID,id);
    }

    @When("I fetch Id from \"{salesforceObject}\" created by currently fetched user")
    public void fetchIdFromSalesforceObjectByCreatedByUser(String salesforceObject) {
        String fetchedUserId = testContext.getSharedData().getData(SharedDataKeys.REFERENCE_ID).toString();
        String query = "SELECT Id from " + salesforceObject + " WHERE CreatedById = '" + fetchedUserId + "'";

        Map<String,Class<? extends Identifiable>> typeMap = Map.of(
                "Account", AccountFields.class,
                "Product2", ProductFields.class,
                "Contact", ContactFields.class,
                    "Asset", AssetFields.class,
                    "User", UserFields.class
                );

        List<Identifiable> fetchedSalesforceObjects = new ArrayList<>(testContext.getHttpClient().fetchData(
                testContext.getJSON().getString("environment"),
                testContext.getJSON().getJSONObject("apiData").getString("queryEndpoint"),
                testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                query,
                typeMap.get(salesforceObject)));

        if(fetchedSalesforceObjects.isEmpty())
            return;

        List<String> ids = new ArrayList<>();
        for(Identifiable object: fetchedSalesforceObjects)
            ids.add(object.getId());

        testContext.getSharedData().setData(SharedDataKeys.FETCHED_SOBJECTS_IDS,String.join( ",",ids));
    }

    @Given("I generate authorization token")
    public void generateToken() {
        testContext.getSharedData().setData(SharedDataKeys.AUTHORIZATION_TOKEN,
                testContext.getHttpClient().generateAuthorizationToken(
                        testContext.getJSON().getString("environment"),
                        testContext.getJSON().getJSONObject("apiData").getString("authEndpoint"),
                        testContext.getJSON().getString("salesforceConsumerKey"),
                        testContext.getJSON().getString("salesforceConsumerSecret")
                ));
    }

    @When("I update {string} in {string} with {string} value")
    public void updateFieldFromSalesforceObject(String fields, String salesforceObject, String values) {
        String[] fieldsArray = fields.split(",");
        String[] valuesArray = values.split(",");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < fieldsArray.length; i++) {
            map.put(fieldsArray[i].trim(), valuesArray[i].trim());
        }

        testContext.getHttpClient().updateFieldsFromSalesforceObject(
                testContext.getJSON().getString("environment"),
                testContext.getJSON().getJSONObject("apiData").getJSONObject(salesforceObject).getString("url"),
                testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                testContext.getSharedData().getData(SharedDataKeys.REFERENCE_ID).toString(),
                map
        );


    }
    @Given("I create Account with API")
    public void createAccountWithApi() {
        JSONObject accountData = testContext.getRandomDataGenerator().generateAccountData(testContext.getJSON().getJSONObject("apiData").getJSONObject("account").getJSONObject("defaultData"));

        testContext.getHttpClient().createSalesforceObject(
                testContext.getJSON().getString("environment"),
                testContext.getJSON().getJSONObject("apiData").getJSONObject("account").getString("url"),
                testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                accountData
        );
    }

    @Given("I create Contact with API")
    public void createContactWithApi() {
        JSONObject contactData = testContext.getRandomDataGenerator().generateContactData(testContext.getJSON().getJSONObject("apiData").getJSONObject("contact").getJSONObject("defaultData"));
        contactData.put("AccountId",testContext.getSharedData().getData(SharedDataKeys.REFERENCE_ID).toString());


        String id = testContext.getHttpClient().createSalesforceObject(
                testContext.getJSON().getString("environment"),
                testContext.getJSON().getJSONObject("apiData").getJSONObject("contact").getString("url"),
                testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                contactData
        );
        testContext.getSharedData().setData(SharedDataKeys.REFERENCE_ID,id);
    }

    @Given("I create Asset with API")
    public void createAssetWithApi() {
        JSONObject assetData = testContext.getRandomDataGenerator().generateAssetData(testContext.getJSON().getJSONObject("apiData").getJSONObject("asset").getJSONObject("defaultData"));
        assetData.put("AccountId",testContext.getSharedData().getData(SharedDataKeys.ACCOUNT_ID).toString());
        assetData.put("Product2Id",testContext.getSharedData().getData(SharedDataKeys.PRODUCT_ID).toString());
        assetData.put("ContactId",testContext.getSharedData().getData(SharedDataKeys.CONTACT_ID).toString());


        String id = testContext.getHttpClient().createSalesforceObject(
                testContext.getJSON().getString("environment"),
                testContext.getJSON().getJSONObject("apiData").getJSONObject("asset").getString("url"),
                testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                assetData
        );
        testContext.getSharedData().setData(SharedDataKeys.REFERENCE_ID,id);
    }

    @Given("I create Product with API")
    public void createProductWithApi() {
        JSONObject productData = testContext.getRandomDataGenerator().generateProductData(testContext.getJSON().getJSONObject("apiData").getJSONObject("product").getJSONObject("defaultData"));

        testContext.getHttpClient().createSalesforceObject(
                testContext.getJSON().getString("environment"),
                testContext.getJSON().getJSONObject("apiData").getJSONObject("product").getString("url"),
                testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                productData
        );
    }

    @Given("I remove all {string} records created by currently fetched user")
    public void removeAllRecordsCreatedWith(String object) {
        Object fetchedObjectsIds = testContext.getSharedData().getData(SharedDataKeys.FETCHED_SOBJECTS_IDS);
        if(fetchedObjectsIds == null)
            return;

        List<String> fetchedRecordsIds = List.of(fetchedObjectsIds.toString().split(","));

        for(String id: fetchedRecordsIds){
            testContext.getHttpClient().deleteSalesforceObject(
                    testContext.getJSON().getString("environment"),
                    testContext.getJSON().getJSONObject("apiData").getJSONObject(object.toLowerCase()).getString("url"),
                    testContext.getSharedData().getData(SharedDataKeys.AUTHORIZATION_TOKEN).toString(),
                    id
            );
        }
    }

}
