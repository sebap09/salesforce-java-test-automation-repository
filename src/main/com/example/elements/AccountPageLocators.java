package com.example.elements;


public class AccountPageLocators {
    public static final String DROP_DOWN_BUTTON = "//button[@role='combobox' and contains(@aria-label,'%s')]";
    public static final String DROP_DOWN_OPTIONS = "//div[contains(@class,'slds-is-open')]/div[contains(@aria-label,'%s')]/lightning-base-combobox-item";
    public static final String DROP_DOWN_OPTION = "//div[contains(@class,'slds-is-open')]/div[contains(@aria-label,'%s')]/lightning-base-combobox-item[@data-value='%s']";
}
