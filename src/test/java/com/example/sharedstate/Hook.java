package com.example.sharedstate;

import com.example.loaders.JSONLoader;
import com.example.utils.PrimaryActionsBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook {
    private TestContext testContext;

    public void beforeAllHook() {
        this.testContext = TestContext.getInstance();


        testContext.getLogger().info("********BEFORE ALL HOOK********");
        JSONLoader jsonLoader = new JSONLoader("src/resources/data-template.json",testContext.getLogger());
        testContext.setJSON(jsonLoader.getJSON());

        testContext.initHelpers();
    }

    public void initSessionHook() {
        testContext.getLogger().info("********INIT SESSION HOOK********");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--disable-notifications");
//        options.addArguments("--window-size=2560,1440");
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);

        PrimaryActionsBot primaryActionsBot = new PrimaryActionsBot(driver, testContext.getLogger());

        testContext.setDriver(driver);
        testContext.setPrimaryActionsBot(primaryActionsBot);
    }

    public void endSessionHook() {
        testContext.getLogger().info("********END SESSION HOOK********");

        testContext.getLogger().info("DRIVER QUIT");
        testContext.getDriver().quit();
    }


}