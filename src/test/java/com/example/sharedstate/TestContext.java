package com.example.sharedstate;

import com.example.api.HttpClient;
import com.example.utils.PrimaryActionsBot;
import com.example.utils.RandomDataGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

@Getter
public final class TestContext {
    //data shared between steps, hooks, scenarios is a singleton
    private static TestContext instance;

    //Singleton Pattern
    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
    private TestContext() {
        this.hook = new Hook();
        this.sharedData = new SharedData();
    }


    private final Hook hook;

    private final SharedData sharedData;

    private HttpClient httpClient;

    private RandomDataGenerator randomDataGenerator;

    @Setter
    private PrimaryActionsBot primaryActionsBot;

    @Setter
    private WebDriver driver;

    private final Logger logger = LogManager.getLogger(TestContext.class);

    @Setter
    private JSONObject JSON;

    public void initHelpers(){
        this.randomDataGenerator = new RandomDataGenerator();
        this.httpClient = new HttpClient(logger);
    }
}