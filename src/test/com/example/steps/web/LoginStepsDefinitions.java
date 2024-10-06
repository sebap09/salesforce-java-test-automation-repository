package com.example.steps.web;

import com.example.pages.CommonPage;
import com.example.pages.LoginPage;
import com.example.sharedstate.TestContext;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepsDefinitions {
    private final TestContext testContext;
    private final LoginPage loginPage;
    private final CommonPage commonPage;
    public LoginStepsDefinitions(){
        this.testContext = TestContext.getInstance();
        this.loginPage = new LoginPage(testContext.getPrimaryActionsBot(), testContext.getDriver());
        this.commonPage = new CommonPage(testContext.getPrimaryActionsBot(), testContext.getDriver());
    }

    @ParameterType("username|password")
    public String loginInputField(String loginInputField){
        return loginInputField;
    }


    @When("I click login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @When("I fill {string} credential to \"{loginInputField}\" input field")
    public void fillLoginCredentials(String user,String field) {
        loginPage.fillLoginInput(field,testContext.getJSON().getJSONObject(user).getString(field));
    }

    @Then("I verify login icon is displayed")
    public void verifyThatLoginIconIsDisplayed() {
        Assert.assertTrue(loginPage.isLoginIconDisplayed());
    }

    @Then("I verify that \"{loginInputField}\" input field is below login icon")
    public void verifyThatInputFieldIsBelowLoginIcon(String inputField) {
        Assert.assertTrue(loginPage.isInputFieldBelowLoginIcon(inputField));
    }

    @Then("I verify that password input field is between username input field and login button")
    public void verifyThatInputFieldIsBelowLoginIcon() {
        Assert.assertTrue(loginPage.isPasswordInputFieldBetweenLoginInputAndLoginButton());
    }

    @Then("I verify that login form is to the left of promo")
    public void verifyThatLoginFormIsToTheLeftOfPromo() {
        Assert.assertTrue(loginPage.isLoginFormToTheLeftOfPromo());
    }

}
