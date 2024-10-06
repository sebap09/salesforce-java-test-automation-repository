package com.example.pages;

import com.example.elements.CommonPageLocators;
import com.example.utils.PrimaryActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CommonPage {
    private final PrimaryActionsBot primaryActionsBot;

    @FindBy(xpath = CommonPageLocators.DETAILS_TAB)
    private WebElement detailsTab;
    @FindBy(xpath = CommonPageLocators.DETAILS_TAB_CONTENT)
    private WebElement detailsTabContent;
    @FindBy(xpath = CommonPageLocators.MENU_BUTTON)
    private WebElement menuButton;
    @FindBy(xpath = CommonPageLocators.MENU_BUTTON_CONTENT)
    private WebElement menuButtonContent;

    public CommonPage(PrimaryActionsBot primaryActionsBot, WebDriver driver){
        this.primaryActionsBot = primaryActionsBot;

        PageFactory.initElements(driver,this);
    }

    public void waitForAPageToLoad(){
        primaryActionsBot.waitForAPageToLoad();
    }

    public void fillValueToInputFieldByName(String value, String name){
        By xpath = By.xpath(String.format(CommonPageLocators.INPUT_FIELD_BY_NAME,name));
        primaryActionsBot.fillValueToElement(xpath,value);
    }

    public String getFieldValue(String label){
        By xpath = By.xpath(String.format(CommonPageLocators.DETAILS_FIELD_BY_LABEL,label,label));
        return primaryActionsBot.getElementAttribute(xpath,"innerText");
    }

    public void fillValueToTextAreaByName(String value, String name){
        By xpath = By.xpath(String.format(CommonPageLocators.TEXT_AREA_BY_NAME,name));
        primaryActionsBot.fillValueToElement(xpath,value);
    }

    public void fillValueToTextAreaByLabelText(String value, String label){
        By xpath = By.xpath(String.format(CommonPageLocators.TEXT_AREA_BY_LABEL,label));

        primaryActionsBot.scrollToElement(xpath,true);
        primaryActionsBot.fillValueToElement(xpath,value);
    }

    public void clickOnDetailsTab() {
        primaryActionsBot.clickElement(detailsTab);
        primaryActionsBot.waitForElementToBeDisplayed(detailsTabContent,Duration.ofSeconds(5));
    }

    public void clickOnMenuButton() {
        primaryActionsBot.clickElement(menuButton);
        primaryActionsBot.waitForElementToBeDisplayed(menuButtonContent,Duration.ofSeconds(2));
    }

    public void clickDataTargetSelectionName(String element, String object, String label){
        By xpath = By.xpath(String.format(CommonPageLocators.DATA_TARGET_SELECTION_NAME,element,object,label));
        primaryActionsBot.clickElement(xpath);
    }

    public String getSuccessToastText() {
        By xpath = By.xpath(String.format(CommonPageLocators.SUCCESS_TOAST));
        primaryActionsBot.waitForElementToBeDisplayed(xpath,Duration.ofSeconds(2));

        return primaryActionsBot.getElementText(xpath);
    }

    public void clickButtonByName(String buttonName) {
        By xpath = By.xpath(String.format(CommonPageLocators.BUTTON_BY_NAME,buttonName));
        primaryActionsBot.clickElement(xpath);
    }

    public void toggleCheckboxByName(String checkboxLabel, Boolean booleanValue) {
        By xpath = By.xpath(String.format(CommonPageLocators.INPUT_FIELD_BY_NAME,checkboxLabel));

        boolean currentStatus = primaryActionsBot.isElementSelected(xpath);

        if(currentStatus!=booleanValue)
            primaryActionsBot.clickElement(xpath);
    }

    public void selectValueFromComboBox(String comboBoxValue, String comboBoxLabel) {
        fillValueToComboBox(comboBoxValue, comboBoxLabel);
        waitForComboBoxValuesToBeDisplayed(comboBoxValue);
        selectValueFromComboBox(comboBoxValue);
        waitForValueToBeSelected(comboBoxValue, comboBoxLabel);
    }

    private void waitForValueToBeSelected(String comboBoxValue, String comboBoxLabel) {
        By xpath = By.xpath(String.format(CommonPageLocators.COMBO_BOX_BY_LABEL,comboBoxLabel));
        primaryActionsBot.waitForElementToHaveAnAttribute(xpath,Duration.ofSeconds(30),"data-value",comboBoxValue);
    }

    private void waitForComboBoxValuesToBeDisplayed(String comboBoxValue){
        By xpath = By.xpath(String.format(CommonPageLocators.ITEM_FROM_COMBO_BOX_DROPDOWN,comboBoxValue));
        primaryActionsBot.waitForElementToBeDisplayed(xpath,Duration.ofSeconds(30));
    }

    private void selectValueFromComboBox(String comboBoxValue){
        By xpath = By.xpath(String.format(CommonPageLocators.ITEM_FROM_COMBO_BOX_DROPDOWN,comboBoxValue));
        primaryActionsBot.clickElement(xpath);
    }

    private void fillValueToComboBox(String comboBoxValue, String comboBoxLabel){
        By xpath = By.xpath(String.format(CommonPageLocators.COMBO_BOX_BY_LABEL,comboBoxLabel));
        primaryActionsBot.fillValueToElement(xpath,comboBoxValue);
    }

    public void fillValueToTextArea(String value, String name){
        By xpath = By.xpath(String.format(CommonPageLocators.PRODUCT_TEXT_AREA,name));
        primaryActionsBot.fillValueToElement(xpath,value);
    }
}
