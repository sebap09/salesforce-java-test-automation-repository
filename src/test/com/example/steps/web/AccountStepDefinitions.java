package com.example.steps.web;

import com.example.pages.AccountPage;
import com.example.pages.CommonPage;
import com.example.sharedstate.SharedDataKeys;
import com.example.sharedstate.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class AccountStepDefinitions {
    private final TestContext testContext;
    private final AccountPage accountPage;
    private final CommonPage commonPage;
    public AccountStepDefinitions(){
        this.testContext = TestContext.getInstance();
        this.accountPage = new AccountPage(testContext.getPrimaryActionsBot());
        this.commonPage = new CommonPage(testContext.getPrimaryActionsBot(), testContext.getDriver());
    }


    @ParameterType("Technology Partner|Banking")
    public String dropDownValue(String dropDownValue){
        return dropDownValue;
    }
    @ParameterType("Type|Industry")
    public String dropDownCategory(String dropDownCategory){
        return dropDownCategory;
    }
    @ParameterType("RANDOM_ACCOUNT_NAME|RANDOM_PHONE_NUMBER|RANDOM_STREET|RANDOM_POSTAL_CODE|RANDOM_CITY|RANDOM_PROVINCE|RANDOM_COUNTRY|RANDOM_DESCRIPTION")
    public String accountValueToBeGenerated(String value){
        return value;
    }
    @ParameterType("Name|Phone|postalCode|city|province|country")
    public String inputAccountFields(String inputAccountField){
        return inputAccountField;
    }
    @ParameterType("Billing Street|Description")
    public String textAreaAccountFields(String textAreaAccountField){
        return textAreaAccountField;
    }


    @When("I fill \"{accountValueToBeGenerated}\" to \"{textAreaAccountFields}\" account form text area")
    public void fillValueToAccountTextArea(String accountValueToBeGenerated, String textAreaAccountFields) {
        JSONObject accountData = testContext.getRandomDataGenerator().generateAccountData(testContext.getJSON().getJSONObject("apiData").getJSONObject("account").getJSONObject("defaultData"));

        Map<SharedDataKeys,String> generatedData = new HashMap<>();
        generatedData.put(SharedDataKeys.RANDOM_STREET,accountData.getString("BillingStreet"));
        generatedData.put(SharedDataKeys.RANDOM_DESCRIPTION,accountData.getString("Description"));

        SharedDataKeys sharedDataKeysFromString = SharedDataKeys.valueOf(accountValueToBeGenerated);
        String generatedValue = generatedData.get(sharedDataKeysFromString);
        testContext.getSharedData().setData(sharedDataKeysFromString,generatedValue);

        commonPage.fillValueToTextAreaByLabelText(generatedValue,textAreaAccountFields);
    }

    @When("I fill \"{accountValueToBeGenerated}\" to \"{inputAccountFields}\" account form input field")
    public void fillValueToAccountInputForm(String accountValueToBeGenerated, String inputAccountFields) {
        JSONObject accountData = testContext.getRandomDataGenerator().generateAccountData(testContext.getJSON().getJSONObject("apiData").getJSONObject("account").getJSONObject("defaultData"));

        Map<SharedDataKeys,String> generatedData = new HashMap<>();
        generatedData.put(SharedDataKeys.RANDOM_ACCOUNT_NAME,accountData.getString("Name"));
        generatedData.put(SharedDataKeys.RANDOM_PHONE_NUMBER,accountData.getString("Phone"));
        generatedData.put(SharedDataKeys.RANDOM_POSTAL_CODE,accountData.getString("BillingPostalCode"));
        generatedData.put(SharedDataKeys.RANDOM_CITY,accountData.getString("BillingCity"));
        generatedData.put(SharedDataKeys.RANDOM_PROVINCE,accountData.getString("BillingState"));
        generatedData.put(SharedDataKeys.RANDOM_COUNTRY,accountData.getString("BillingCountry"));

        SharedDataKeys sharedDataKeysFromString = SharedDataKeys.valueOf(accountValueToBeGenerated);
        String generatedValue = generatedData.get(sharedDataKeysFromString);
        testContext.getSharedData().setData(sharedDataKeysFromString,generatedValue);

        commonPage.fillValueToInputFieldByName(generatedValue,inputAccountFields);
    }

    @When("I select \"{dropDownValue}\" from \"{dropDownCategory}\" object form dropdown")
    public void selectValueFromDropdown(String dropDownValue, String dropDownCategory) {
        accountPage.selectValueFromDropDown(dropDownValue,dropDownCategory);

        switch (dropDownCategory){
            case "Type":
                testContext.getSharedData().setData(SharedDataKeys.ACCOUNT_TYPE,dropDownValue);
                break;
            case "Industry":
                testContext.getSharedData().setData(SharedDataKeys.ACCOUNT_INDUSTRY,dropDownValue);
                break;
        }
    }
}
