package com.runner;

//import com.frameworks.ResultUploaderToAIOTest;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.AfterClass;
//import org.junit.runner.RunWith;
//
//import java.io.File;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/resources/features", glue = "com.stepdefinitions", plugin = { "pretty",
//        "html:target/cucumber-reports.html", "json:target/cucumber-reports.json" })
//public class TestRunner {
//    @AfterClass
//    public static void uploadTestResultsAndScreenshots() {
//        File cucumberJsonFile = new File("target/cucumber-reports.json");
//
//        System.out.println("Cucumber JSON file path: " + cucumberJsonFile.getAbsolutePath());
//        System.out.println("File exists: " + cucumberJsonFile.exists());
//        if (cucumberJsonFile.exists()) {
//            System.out.println("Uploading Cucumber JSON test results...");
//            ResultUploaderToAIOTest uploader = new ResultUploaderToAIOTest();
//            uploader.uploadTestResults(cucumberJsonFile);
//        } else {
//            System.err.println("Cucumber JSON file not found: " + cucumberJsonFile.getAbsolutePath());
//            return;
//        }
//    }
//
//}
import com.frameworks.ResultUploaderToAIOTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.stepdefinitions", plugin = { "pretty",
        "html:target/cucumber-reports.html", "json:target/cucumber-reports.json" })
public class TestRunner {
    @AfterClass
    public static void uploadTestResultsAndScreenshots() {
        File cucumberJsonFile = new File("target/cucumber-reports.json");

        System.out.println("Cucumber JSON file path: " + cucumberJsonFile.getAbsolutePath());
        System.out.println("File exists: " + cucumberJsonFile.exists());
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
