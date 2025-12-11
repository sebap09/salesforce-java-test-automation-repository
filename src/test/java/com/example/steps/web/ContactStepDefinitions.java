package com.example.steps.web;

import com.example.pages.CommonPage;
import com.example.pages.ContactPage;
import com.example.sharedstate.SharedDataKeys;
import com.example.sharedstate.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactStepDefinitions {
    private final TestContext testContext;
    private final ContactPage contactPage;
    private final CommonPage commonPage;
    public ContactStepDefinitions(){
        this.testContext = TestContext.getInstance();
        this.contactPage = new ContactPage(testContext.getPrimaryActionsBot());
        this.commonPage = new CommonPage(testContext.getPrimaryActionsBot(), testContext.getDriver());
    }


    @ParameterType("RANDOM_FIRST_NAME|RANDOM_LAST_NAME|RANDOM_EMAIL|RANDOM_MOBILE")
    public String contactValueToBeGenerated(String value){
        return value;
    }
    @ParameterType("firstName|lastName|Email|MobilePhone")
    public String inputContactFields(String inputContactFields){
        return inputContactFields;
    }

    @When("I fill \"{contactValueToBeGenerated}\" to \"{inputContactFields}\" contact form input field")
    public void fillValueToContactInputForm(String contactValueToBeGenerated, String inputContactFields) {
        JSONObject contactData = testContext.getRandomDataGenerator().generateContactData(testContext.getJSON().getJSONObject("apiData").getJSONObject("contact").getJSONObject("defaultData"));

        Map<SharedDataKeys,String> generatedData = new HashMap<>();
        generatedData.put(SharedDataKeys.RANDOM_FIRST_NAME,contactData.getString("FirstName"));
        generatedData.put(SharedDataKeys.RANDOM_LAST_NAME,contactData.getString("LastName"));
        generatedData.put(SharedDataKeys.RANDOM_EMAIL,contactData.getString("Email"));
        generatedData.put(SharedDataKeys.RANDOM_MOBILE,contactData.getString("MobilePhone"));

        SharedDataKeys sharedDataKeysFromString = SharedDataKeys.valueOf(contactValueToBeGenerated);
        String generatedValue = generatedData.get(sharedDataKeysFromString);
        testContext.getSharedData().setData(sharedDataKeysFromString,generatedValue);

        commonPage.fillValueToInputFieldByName(generatedValue,inputContactFields);
    }

    @When("I map First Name and Last Name to Full Name field")
    public void mapToFullNameField() {
        String firstName = testContext.getSharedData().getData(SharedDataKeys.RANDOM_FIRST_NAME).toString();
        String lastName = testContext.getSharedData().getData(SharedDataKeys.RANDOM_LAST_NAME).toString();

        testContext.getSharedData().setData(SharedDataKeys.CONTACT_FULL_NAME,firstName + " " + lastName);
    }
}
