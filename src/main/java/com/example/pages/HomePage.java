package com.example.pages;

import com.example.elements.HomePageLocators;
import com.example.utils.PrimaryActionsBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage {
    private final PrimaryActionsBot primaryActionsBot;

    @FindBy(xpath = HomePageLocators.APP_LAUNCHER)
    private WebElement appLauncherButton;

    public HomePage(PrimaryActionsBot primaryActionsBot, WebDriver driver){
        this.primaryActionsBot = primaryActionsBot;

        PageFactory.initElements(driver,this);
    }


    public boolean isAppLauncherVisible(){
        primaryActionsBot.waitForElementToBeDisplayed(appLauncherButton, Duration.ofSeconds(5));
       return primaryActionsBot.isElementDisplayed(appLauncherButton);
    }
}
