package com.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.aiointegration.ResultUploaderToAIOTest;
import com.utils.ConfigReader;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {
        "com.stepdefinitions", "com.hooks" }, plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports.json"
        }, publish = true)
public class TestRunner {
    @AfterClass
    public static void uploadTestResultsAndScreenshots() throws IOException {
        File cucumberJsonFile = new File("target/cucumber-reports.json");
        if (cucumberJsonFile.exists()) {
            System.out.println("Uploading Cucumber JSON test results...");
            String aioToken = null;
            String gitToken = null;
            aioToken = System.getenv("AIO_TOKEN") != null ? System.getenv("AIO_TOKEN")
                    : ConfigReader.loadEnv("AIO_TOKEN");
            gitToken = System.getenv("GIT_TOKEN") != null ? System.getenv("GIT_TOKEN")
                    : ConfigReader.loadEnv("GIT_TOKEN");
            ResultUploaderToAIOTest resultUploader = new ResultUploaderToAIOTest(aioToken, gitToken);
            resultUploader.uploadTestResults(cucumberJsonFile);
        } else {
            System.err.println("Cucumber JSON file not found: " + cucumberJsonFile.getAbsolutePath());
            return;
        }
    }
}
