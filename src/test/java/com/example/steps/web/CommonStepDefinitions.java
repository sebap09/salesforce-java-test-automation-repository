package com.example.steps.web;

import com.example.pages.CommonPage;
import com.example.sharedstate.SharedDataKeys;
import com.example.sharedstate.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Map;

public class CommonStepDefinitions {
    private final TestContext testContext;
    private final CommonPage commonPage;
    public CommonStepDefinitions(){
        this.testContext = TestContext.getInstance();
        this.commonPage = new CommonPage(testContext.getPrimaryActionsBot(), testContext.getDriver());
    }


    @ParameterType("SaveEdit")
    public String buttonName(String buttonName){
        return buttonName;
    }
    @ParameterType("accountCreated|accountSaved|productCreated|productSaved|contactCreated|assetCreated")
    public String toastMessage(String toastMessage){
        return toastMessage;
    }
    @ParameterType("true|false")
    public Boolean booleanValue(String booleanValue){
        return Boolean.valueOf(booleanValue);
    }
    @ParameterType("IsActive|IsCompetitorProduct")
    public String checkboxLabel(String checkboxLabel){
        return checkboxLabel;
    }
    @ParameterType("Account Name|Account|Product|Contact")
    public String comboBoxFields(String comboBoxFields){
        return comboBoxFields;
    }
    @ParameterType("ACCOUNT_NAME|PRODUCT_NAME|CONTACT_NAME")
    public String sharedName(String sharedName){
        return sharedName;
    }
    @ParameterType("Account Name|Type|Industry|Phone|Billing Address|Description|Product Name|Product Code|Product Description|Active|Name|Email|Mobile|Asset Name|Serial Number|Competitor Asset|Account|Contact|Product")
    public String detailsField(String detailsField){
        return detailsField;
    }

    @Given("I am on the {word} page")
    public void openPage(String pageType) {
        testContext.getDriver().get(testContext.getJSON().getJSONObject("pageUrls").getString(pageType));
    }
    @When("I open details page of object by fetched id")
    public void openObjectDetailsPageById() {
        testContext.getDriver().get(
                String.format(
                        testContext.getJSON().getJSONObject("pageUrls").getString("objectDetails"),
                        testContext.getSharedData().getData(SharedDataKeys.REFERENCE_ID)
                )
        );
    }
    @When("I click on details tab")
    public void clickOnDetailsTab() {
        commonPage.clickOnDetailsTab();

    }

    @When("I click on menu button")
    public void clickOnMenuButton() {
        commonPage.clickOnMenuButton();
    }

    @When("I click data target selection name of type {string}, {string} and {string}")
    public void clickDataTargetSelection(String element, String object, String label) {
        commonPage.clickDataTargetSelectionName(element,object,label);
    }

    @When("I wait for a page to load")
    public void waitForAPageToLoad() {
        commonPage.waitForAPageToLoad();
    }

    @Then("I verify success toast with \"{toastMessage}\" message")
    public void verifySuccessToast(String toastMessage) {
        String message = testContext.getJSON().getJSONObject("validationMessages").getString(toastMessage);

        Map<String, SharedDataKeys> map = Map.of(
                "accountCreated", SharedDataKeys.RANDOM_ACCOUNT_NAME,
                "productCreated", SharedDataKeys.RANDOM_PRODUCT_NAME,
                "contactCreated", SharedDataKeys.CONTACT_FULL_NAME,
                "accountSaved", SharedDataKeys.ACCOUNT_NAME,
                "productSaved", SharedDataKeys.PRODUCT_NAME,
                "assetCreated", SharedDataKeys.RANDOM_ASSET_NAME
        );

        message=message.replace("PLACEHOLDER",testContext.getSharedData().getData(map.get(toastMessage)).toString());


        Assert.assertEquals(commonPage.getSuccessToastText(),message);
    }

    @When("I click \"{buttonName}\" button")
    public void clickButton(String buttonName) {
        commonPage.clickButtonByName(buttonName);
    }

    @When("I toggle checkbox with \"{checkboxLabel}\" name to \"{booleanValue}\"")
    public void toggleCheckbox(String checkboxLabel, Boolean booleanValue) {
        commonPage.toggleCheckboxByName(checkboxLabel,booleanValue);

        switch (checkboxLabel){
            case "IsActive":
                testContext.getSharedData().setData(SharedDataKeys.PRODUCT_IS_ACTIVE,booleanValue ? "Active" : "");
                break;
            case "IsCompetitorProduct":
                testContext.getSharedData().setData(SharedDataKeys.ASSET_IS_COMPETITOR,booleanValue ? "Competitor Asset" : "");
                break;
        }


    }

    @When("I select \"{sharedName}\" from \"{comboBoxFields}\" form combo box")
    public void fillValueToComboBox(String sharedName, String comboBoxFields) {
        Map<String, SharedDataKeys> map = Map.of(
                "ACCOUNT_NAME", SharedDataKeys.RANDOM_ACCOUNT_NAME,
                "PRODUCT_NAME", SharedDataKeys.RANDOM_PRODUCT_NAME,
                "CONTACT_NAME", SharedDataKeys.CONTACT_FULL_NAME
        );

        commonPage.selectValueFromComboBox(testContext.getSharedData().getData(map.get(sharedName)).toString(),comboBoxFields);
    }

    @Then("I verify that correct data is visible in \"{detailsField}\" field")
    public void verifyDataInField(String detailsField) {
        String expectedValue = switch (detailsField) {
            case "Account Name", "Account" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_ACCOUNT_NAME).toString();
            case "Phone" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_PHONE_NUMBER).toString();
            case "Type" -> testContext.getSharedData().getData(SharedDataKeys.ACCOUNT_TYPE).toString();
            case "Industry" -> testContext.getSharedData().getData(SharedDataKeys.ACCOUNT_INDUSTRY).toString();
            case "Description" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_DESCRIPTION).toString();
            case "Billing Address" -> String.format(
                    "%s\n%s %s\n%s %s",
                    testContext.getSharedData().getData(SharedDataKeys.RANDOM_STREET).toString(),
                    testContext.getSharedData().getData(SharedDataKeys.RANDOM_POSTAL_CODE).toString(),
                    testContext.getSharedData().getData(SharedDataKeys.RANDOM_CITY).toString(),
                    testContext.getSharedData().getData(SharedDataKeys.RANDOM_PROVINCE).toString(),
                    testContext.getSharedData().getData(SharedDataKeys.RANDOM_COUNTRY).toString()
            );
            case "Product Name", "Product" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_PRODUCT_NAME).toString();
            case "Product Code" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_PRODUCT_CODE).toString();
            case "Product Description" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_PRODUCT_DESCRIPTION).toString();
            case "Active" -> testContext.getSharedData().getData(SharedDataKeys.PRODUCT_IS_ACTIVE).toString();
            case "Name" -> String.format(
                    "%s %s",
                    testContext.getSharedData().getData(SharedDataKeys.RANDOM_FIRST_NAME).toString(),
                    testContext.getSharedData().getData(SharedDataKeys.RANDOM_LAST_NAME).toString());
            case "Mobile" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_MOBILE).toString();
            case "Email" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_EMAIL).toString();
            case "Asset Name" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_ASSET_NAME).toString();
            case "Serial Number" -> testContext.getSharedData().getData(SharedDataKeys.RANDOM_SERIAL_NUMBER).toString();
            case "Competitor Asset" -> testContext.getSharedData().getData(SharedDataKeys.ASSET_IS_COMPETITOR).toString();
            case "Contact" -> testContext.getSharedData().getData(SharedDataKeys.CONTACT_FULL_NAME).toString();
            default -> null;
        };

        Assert.assertEquals(commonPage.getFieldValue(detailsField),expectedValue);
    }
}