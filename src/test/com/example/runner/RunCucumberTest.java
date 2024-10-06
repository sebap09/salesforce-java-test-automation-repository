package com.example.runner;


import com.example.loaders.CucumberRunnerConfigLoader;
import io.cucumber.core.cli.Main;
import io.cucumber.testng.AbstractTestNGCucumberTests;


public class RunCucumberTest extends AbstractTestNGCucumberTests {
    public static void main(String[] args) {
        CucumberRunnerConfigLoader cucumberRunnerConfigLoader = new CucumberRunnerConfigLoader("test-configuration-template.yaml");
        Main.main(cucumberRunnerConfigLoader.getOptions());
    }
}