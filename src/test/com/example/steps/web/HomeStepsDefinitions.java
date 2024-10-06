package com.example.steps.web;

import com.example.pages.HomePage;
import com.example.sharedstate.TestContext;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class HomeStepsDefinitions {
    private final TestContext testContext;
    private final HomePage homePage;
    public HomeStepsDefinitions(){
        this.testContext = TestContext.getInstance();
        this.homePage = new HomePage(testContext.getPrimaryActionsBot(), testContext.getDriver());
    }


    @Then("I verify user is correctly logged in")
    public void verifyUserIsLoggedIn() {
        Assert.assertTrue(homePage.isAppLauncherVisible());
    }
}
