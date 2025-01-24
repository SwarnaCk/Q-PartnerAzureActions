package com.runner;

import io.cucumber.java.AfterAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.aiointegration.ResultUploaderToAIOTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
                 glue = {"com.stepdefinitions","com.hooks"},
                 plugin = {
                        "pretty", 
                        "html:target/cucumber-reports.html",
                        "json:target/cucumber-reports.json"
                })
public class TestRunner {
    @AfterClass
    public static void uploadTestResultsAndScreenshots() {
        File cucumberJsonFile = new File("target/cucumber-reports.json");
        if (cucumberJsonFile.exists()) {
            System.out.println("Uploading Cucumber JSON test results...");
            ResultUploaderToAIOTest uploader = new ResultUploaderToAIOTest();
            uploader.uploadTestResults(cucumberJsonFile);
        } else {
            System.err.println("Cucumber JSON file not found: " + cucumberJsonFile.getAbsolutePath());
            return;
        }
    }
}

