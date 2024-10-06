package com.example.elements;

public class CommonPageLocators {
    public static final String TEXT_AREA_BY_NAME = "//textarea[@name='%s']";
    public static final String TEXT_AREA_BY_LABEL = "//lightning-textarea//label[text()='%s']/following-sibling::*//textarea";
    public static final String DETAILS_TAB = "//a[@data-label='Details']";
    public static final String DETAILS_TAB_CONTENT = "//div[contains(@class,'active')]//flexipage-component2[@data-component-id='force_detailPanel']";
    public static final String MENU_BUTTON = "//lightning-button-menu[contains(@class,'menu-button')]";
    public static final String MENU_BUTTON_CONTENT = MENU_BUTTON + "/div[contains(@class,'dropdown')]";
    public static final String DATA_TARGET_SELECTION_NAME = "//*[@data-target-selection-name='sfdc:%s.%s.%s']";
    public static final String SUCCESS_TOAST = "//div[@role='alert' and @data-key='success']//div[contains(@class,'toastContent')]//div[contains(@id,toastDescription)]//span";
    public static final String BUTTON_BY_NAME = "//button[@name='%s']";
    public static final String INPUT_FIELD_BY_NAME = "//input[@name='%s']";
    public static final String DETAILS_FIELD_BY_LABEL = "//div[contains(@class,'active')]//records-record-layout-item[@field-label='%s']//*[starts-with(local-name(), 'lightning') and @data-output-element-id] | //div[contains(@class,'active')]//records-record-layout-item[@field-label='%s']//*[@data-output-element-id]//a";
    public static final String COMBO_BOX_BY_LABEL = "//lightning-grouped-combobox/label[text()='%s']/..//input";
    public static final String ITEM_FROM_COMBO_BOX_DROPDOWN = "//lightning-base-combobox-item//*[@title='%s']";
    public static final String PRODUCT_TEXT_AREA = "//records-record-layout-item[@field-label='%s']//textarea";
}
