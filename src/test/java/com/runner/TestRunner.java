package com.runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import java.io.File;

import org.testng.annotations.AfterSuite;

import com.aiointegration.ResultUploaderToAIOTest;


@CucumberOptions(features = "src/test/resources/features",
                 glue = {"com.stepdefinitions","com.hooks"},
                 plugin = {
                        "pretty", 
                        "html:target/cucumber-reports.html",
                        "json:target/cucumber-reports.json"
                })
public class TestRunner extends AbstractTestNGCucumberTests {
        @AfterSuite
    public static void uploadTestResultsAndScreenshots() {
        File cucumberJsonFile = new File("target/cucumber-reports.json");

        // System.out.println("Cucumber JSON file path: " + cucumberJsonFile.getAbsolutePath());
        // System.out.println("File exists: " + cucumberJsonFile.exists());
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

