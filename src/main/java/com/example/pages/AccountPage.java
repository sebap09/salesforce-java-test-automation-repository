package com.example.pages;

import com.example.elements.AccountPageLocators;
import com.example.utils.PrimaryActionsBot;
import org.openqa.selenium.By;

import java.time.Duration;

public class AccountPage {
    private final PrimaryActionsBot primaryActionsBot;

    public AccountPage(PrimaryActionsBot primaryActionsBot) {
        this.primaryActionsBot = primaryActionsBot;
    }

    public void selectValueFromDropDown(String dropDownValue, String dropDownCategory) {
        clickDropDownButton(dropDownCategory);
        waitForDropDownOptionsToBeDisplayed(dropDownCategory);
        clickDropDownOption(dropDownValue, dropDownCategory);
        waitForDropDownButtonToHaveAnAttribute(dropDownValue, dropDownCategory, "data-value");
    }

    private void clickDropDownButton(String dropDownCategory) {
        By xpath = By.xpath(String.format(AccountPageLocators.DROP_DOWN_BUTTON, dropDownCategory));
        primaryActionsBot.clickElement(xpath);
    }

    private void waitForDropDownOptionsToBeDisplayed(String dropDownCategory) {
        By xpath = By.xpath(String.format(AccountPageLocators.DROP_DOWN_OPTIONS, dropDownCategory));
        primaryActionsBot.waitForElementToBeDisplayed(xpath, 0, Duration.ofSeconds(2));
    }

    private void clickDropDownOption(String dropDownValue, String dropDownCategory) {
        By xpath = By.xpath(String.format(AccountPageLocators.DROP_DOWN_OPTION, dropDownCategory, dropDownValue));
        primaryActionsBot.clickElement(xpath);
    }

    private void waitForDropDownButtonToHaveAnAttribute(String dropDownValue, String dropDownCategory, String dropDownAttribute) {
        By xpath = By.xpath(String.format(AccountPageLocators.DROP_DOWN_BUTTON, dropDownCategory));
        primaryActionsBot.waitForElementToHaveAnAttribute(xpath, Duration.ofSeconds(2), dropDownAttribute, dropDownValue);
    }
}
