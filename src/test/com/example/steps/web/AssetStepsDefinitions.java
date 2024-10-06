package com.example.steps.web;

import com.example.pages.AssetPage;
import com.example.pages.CommonPage;
import com.example.sharedstate.SharedDataKeys;
import com.example.sharedstate.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AssetStepsDefinitions {
    private final TestContext testContext;
    private final AssetPage assetPage;
    private final CommonPage commonPage;
    public AssetStepsDefinitions(){
        this.testContext = TestContext.getInstance();
        this.assetPage = new AssetPage(testContext.getPrimaryActionsBot());
        this.commonPage = new CommonPage(testContext.getPrimaryActionsBot(), testContext.getDriver());
    }


    @ParameterType("RANDOM_ASSET_NAME|RANDOM_SERIAL_NUMBER|RANDOM_DESCRIPTION")
    public String assetValueToBeGenerated(String value){
        return value;
    }
    @ParameterType("Name|SerialNumber")
    public String inputAssetFields(String inputAssetFields){
        return inputAssetFields;
    }
    @ParameterType("Description")
    public String textAreaAssetFields(String textAreaProductFields){
        return textAreaProductFields;
    }
    @ParameterType("Account Name")
    public String comboBoxContactFields(String comboBoxContactFields){
        return comboBoxContactFields;
    }


    @When("I fill \"{assetValueToBeGenerated}\" to \"{inputAssetFields}\" asset form input field")
    public void fillValueToAssetInputForm(String assetValueToBeGenerated, String inputAssetFields) {
        JSONObject assetData = testContext.getRandomDataGenerator().generateAssetData(testContext.getJSON().getJSONObject("apiData").getJSONObject("asset").getJSONObject("defaultData"));

        Map<SharedDataKeys,String> generatedData = new HashMap<>();
        generatedData.put(SharedDataKeys.RANDOM_ASSET_NAME,assetData.getString("Name"));
        generatedData.put(SharedDataKeys.RANDOM_SERIAL_NUMBER,assetData.getString("SerialNumber"));

        SharedDataKeys sharedDataKeysFromString = SharedDataKeys.valueOf(assetValueToBeGenerated);
        String generatedValue = generatedData.get(sharedDataKeysFromString);
        testContext.getSharedData().setData(sharedDataKeysFromString,generatedValue);

        commonPage.fillValueToInputFieldByName(generatedValue,inputAssetFields);
    }

    @When("I fill \"{assetValueToBeGenerated}\" to \"{textAreaAssetFields}\" asset form text area")
    public void fillValueToAssetTextArea(String assetValueToBeGenerated, String textAreaAssetFields) {
        JSONObject assetData = testContext.getRandomDataGenerator().generateAssetData(testContext.getJSON().getJSONObject("apiData").getJSONObject("asset").getJSONObject("defaultData"));

        Map<SharedDataKeys,String> generatedData = new HashMap<>();
        generatedData.put(SharedDataKeys.RANDOM_DESCRIPTION,assetData.getString("Description"));

        SharedDataKeys sharedDataKeysFromString = SharedDataKeys.valueOf(assetValueToBeGenerated);
        String generatedValue = generatedData.get(sharedDataKeysFromString);
        testContext.getSharedData().setData(sharedDataKeysFromString,generatedValue);

        commonPage.fillValueToTextArea(generatedValue,textAreaAssetFields);
    }
}
