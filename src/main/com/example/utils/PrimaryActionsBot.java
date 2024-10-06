package com.example.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PrimaryActionsBot {
    private final WebDriver driver;
    private final Logger logger;

    public PrimaryActionsBot(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
    }

    public void clickElement(By xpath){
        logger.info(String.format("CLICKING ELEMENT: %s",xpath));
        scrollToElement(xpath,false);
        WebElement webElement = driver.findElement(xpath);

        webElement.click();
    }

    public void clickElement(WebElement element){
        logger.info(String.format("CLICKING ELEMENT: %s",element));
        element.click();
    }

    public void waitForElementToBeDisplayed(By xpath, int index, Duration duration){
        logger.info(String.format("WAITING FOR ELEMENT: %s, TO BE DISPLAYED IN: %S",xpath,duration));
        List<WebElement> webElement = driver.findElements(xpath);

        Wait<WebDriver> wait = new WebDriverWait(driver, duration);
        wait.until(d -> webElement.get(index).isDisplayed());
    }

    public void waitForElementToBeDisplayed(By xpath, Duration duration){
        logger.info(String.format("WAITING FOR ELEMENT: %s, TO BE DISPLAYED IN: %S",xpath,duration));
        WebElement webElement = driver.findElement(xpath);

        Wait<WebDriver> wait = new WebDriverWait(driver, duration);
        wait.until(d -> webElement.isDisplayed());
    }

    public void waitForElementToBeDisplayed(WebElement element, Duration duration){
        logger.info(String.format("WAITING FOR ELEMENT: %s, TO BE DISPLAYED IN: %S",element,duration));
        Wait<WebDriver> wait = new WebDriverWait(driver, duration);
        wait.until(d -> element.isDisplayed());
    }

    public void waitForElementToHaveAnAttribute(By xpath, Duration duration, String attribute, String value){
        logger.info(String.format("WAITING FOR ELEMENT: %s, TO HAVE AN ATTRIBUTE: %s = %s, IN: %S",xpath,attribute,value,duration));
        WebElement webElement = driver.findElement(xpath);

        Wait<WebDriver> wait = new WebDriverWait(driver, duration);
        wait.until(d -> webElement.getAttribute(attribute).equals(value));
    }

    public void fillValueToElement(By xpath, String value){
        logger.info(String.format("SENDING KEYS: %s TO ELEMENT: %s",value,xpath));
        WebElement webElement = driver.findElement(xpath);

        webElement.clear();
        webElement.sendKeys(value);

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> webElement.getAttribute("value").equals(value));
    }

    public void scrollToElement(By xpath, boolean isBelow){
        String direction = isBelow ? "DOWN" : "UP";
        logger.info(String.format("SCROLLING: %s TO ELEMENT: %s",direction,xpath));
        WebElement element = driver.findElement(xpath);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(" + isBelow + ");", element);
    }

    public String getElementText(By xpath) {
        logger.info(String.format("FETCHING TEXT OF ELEMENT: %s",xpath));
        WebElement element = driver.findElement(xpath);

        return element.getText();
    }

    public String getElementAttribute(By xpath, String attribute) {
        logger.info(String.format("FETCHING ATTRIBUTE: %s OF ELEMENT: %s",attribute,xpath));
        WebElement element = driver.findElement(xpath);

        return element.getAttribute(attribute);
    }

    public boolean isElementSelected(By xpath) {
        logger.info(String.format("CHECKING IF ELEMENT: %s IS SELECTED",xpath));
        WebElement element = driver.findElement(xpath);

        return element.isSelected();
    }

    public boolean isElementDisplayed(By xpath){
        logger.info(String.format("CHECKING IF ELEMENT: %s IS DISPLAYED",xpath));
        WebElement element = driver.findElement(xpath);

        return element.isDisplayed();
    }

    public boolean isElementDisplayed(WebElement element){
        logger.info(String.format("CHECKING IF ELEMENT: %s IS DISPLAYED",element));
        return element.isDisplayed();
    }

    public void waitForAPageToLoad(){
        logger.info("Waiting for a page to load...");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> (
                (JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public boolean isElementDisplayedBelow(By belowXPath, By aboveXPath){
        try {
            By locator = RelativeLocator.with(belowXPath).below(aboveXPath);
            logger.info(String.format("CHECKING IF ELEMENT: %s IS DISPLAYED BELOW ELEMENT: %s", belowXPath, aboveXPath));

            WebElement element = driver.findElement(locator);

            return element.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementDisplayedBetween(By elementXPath, By belowXPath, By aboveXPath) {
        try {
            By locator = RelativeLocator.with(RelativeLocator.with(elementXPath).below(belowXPath)).above(aboveXPath);
            logger.info(String.format("CHECKING IF ELEMENT: %s IS DISPLAYED BETWEEN ELEMENT: %s AND ELEMENT: %s", elementXPath, belowXPath, aboveXPath));

            WebElement element = driver.findElement(locator);

            return element.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementDisplayedToTheLeft(By elementXPath, By elementOnTheRightXPath) {
        try {
            By locator = RelativeLocator.with(elementXPath).toLeftOf(elementOnTheRightXPath);
            logger.info(String.format("CHECKING IF ELEMENT: %s IS DISPLAYED TO THE LEFT OF ELEMENT: %s", elementXPath, elementOnTheRightXPath));

            WebElement element = driver.findElement(locator);

            return element.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }

    }
}
