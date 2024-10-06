package com.example.steps.web;

import com.example.pages.CommonPage;
import com.example.pages.ProductPage;
import com.example.sharedstate.SharedDataKeys;
import com.example.sharedstate.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductStepDefinitions {

    private final TestContext testContext;
    private final ProductPage productPage;
    private final CommonPage commonPage;
    public ProductStepDefinitions(){
        this.testContext = TestContext.getInstance();
        this.productPage = new ProductPage(testContext.getPrimaryActionsBot());
        this.commonPage = new CommonPage(testContext.getPrimaryActionsBot(), testContext.getDriver());
    }


    @ParameterType("RANDOM_PRODUCT_NAME|RANDOM_PRODUCT_CODE|RANDOM_PRODUCT_DESCRIPTION|RANDOM_DESCRIPTION")
    public String productValueToBeGenerated(String value){
        return value;
    }
    @ParameterType("Product Description")
    public String textAreaProductFields(String textAreaProductFields){
        return textAreaProductFields;
    }
    @ParameterType("Name|ProductCode")
    public String inputProductFields(String inputProductFields){
        return inputProductFields;
    }


    @When("I fill \"{productValueToBeGenerated}\" to \"{inputProductFields}\" product form input field")
    public void fillValueToProductInputForm(String productValueToBeGenerated, String inputProductFields) {
        JSONObject productData = testContext.getRandomDataGenerator().generateProductData(testContext.getJSON().getJSONObject("apiData").getJSONObject("product").getJSONObject("defaultData"));

        Map<SharedDataKeys,String> generatedData = new HashMap<>();
        generatedData.put(SharedDataKeys.RANDOM_PRODUCT_NAME,productData.getString("Name"));
        generatedData.put(SharedDataKeys.RANDOM_PRODUCT_CODE,productData.getString("ProductCode"));

        SharedDataKeys sharedDataKeysFromString = SharedDataKeys.valueOf(productValueToBeGenerated);
        String generatedValue = generatedData.get(sharedDataKeysFromString);
        testContext.getSharedData().setData(sharedDataKeysFromString,generatedValue);

        commonPage.fillValueToInputFieldByName(generatedValue,inputProductFields);
    }

    @When("I fill \"{productValueToBeGenerated}\" to \"{textAreaProductFields}\" product form text area")
    public void fillValueToProductTextArea(String valueToBeGenerated, String textAreaProductFields) {
        JSONObject productData = testContext.getRandomDataGenerator().generateProductData(testContext.getJSON().getJSONObject("apiData").getJSONObject("product").getJSONObject("defaultData"));

        Map<SharedDataKeys,String> generatedData = new HashMap<>();
        generatedData.put(SharedDataKeys.RANDOM_PRODUCT_DESCRIPTION,productData.getString("Description"));

        SharedDataKeys sharedDataKeysFromString = SharedDataKeys.valueOf(valueToBeGenerated);
        String generatedValue = generatedData.get(sharedDataKeysFromString);
        testContext.getSharedData().setData(sharedDataKeysFromString,generatedValue);

        commonPage.fillValueToTextArea(generatedValue,textAreaProductFields);
    }
}
