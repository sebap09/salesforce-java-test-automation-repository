package com.example.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/default/",
        glue = {
                "com.example.steps",
                "com.example.sharedstate"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/Cucumber.json"
        },
        monochrome = true  // Readable console output
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    public static void main(String[] args) {
    }
}