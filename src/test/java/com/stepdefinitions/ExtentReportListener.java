package com.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.MediaEntityBuilder;





public class ExtentReportListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

    static {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }


    public void startTest(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        testReport.set(test);
    }

    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()) {
            //testReport.get().fail("Test failed");
        } else {
            testReport.get().pass("Test passed");
        }
    }
    public void attachScreenshot(String screenshotPath) {
        try {
            testReport.get().fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void afterTest() {
        extent.flush();
    }
    
}
