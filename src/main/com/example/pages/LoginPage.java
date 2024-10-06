package com.example.pages;

import com.example.elements.LoginPageLocators;
import com.example.utils.PrimaryActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final PrimaryActionsBot primaryActionsBot;

    @FindBy(xpath = LoginPageLocators.LOGIN_ICON)
    private WebElement loginIcon;

    @FindBy(xpath = LoginPageLocators.LOGIN_PROMO)
    private WebElement loginPromo;

    @FindBy(xpath = LoginPageLocators.LOGIN_FORM)
    private WebElement loginForm;

    public LoginPage(PrimaryActionsBot primaryActionsBot, WebDriver driver){
        this.primaryActionsBot = primaryActionsBot;

        PageFactory.initElements(driver,this);
    }

    public void fillLoginInput(String inputName, String value){
        By xpath = By.xpath(String.format(LoginPageLocators.LOGIN_INPUT,inputName));
        primaryActionsBot.fillValueToElement(xpath,value);
    }

    public void clickLoginButton(){
        By loginButton = By.xpath(String.format(LoginPageLocators.LOGIN_INPUT,"Login"));
        primaryActionsBot.clickElement(loginButton);
    }

    public boolean isLoginIconDisplayed() {
        return primaryActionsBot.isElementDisplayed(loginIcon);
    }

    public boolean isInputFieldBelowLoginIcon(String inputName) {
        By inputXpath = By.xpath(String.format(LoginPageLocators.LOGIN_INPUT,inputName));
        By loginIcon = By.xpath(LoginPageLocators.LOGIN_ICON);
        return primaryActionsBot.isElementDisplayedBelow(inputXpath, loginIcon);
    }

    public boolean isPasswordInputFieldBetweenLoginInputAndLoginButton() {
        By passwordInput = By.xpath(String.format(LoginPageLocators.LOGIN_INPUT,"password"));
        By usernameInput = By.xpath(String.format(LoginPageLocators.LOGIN_INPUT,"username"));
        By loginButton = By.xpath(String.format(LoginPageLocators.LOGIN_INPUT,"Login"));

        return primaryActionsBot.isElementDisplayedBetween(passwordInput,usernameInput,loginButton);
    }

    public boolean isLoginFormToTheLeftOfPromo() {
        By form = By.xpath(LoginPageLocators.LOGIN_FORM);
        By promo = By.xpath(LoginPageLocators.LOGIN_PROMO);
        return primaryActionsBot.isElementDisplayedToTheLeft(form,promo);
    }
}
