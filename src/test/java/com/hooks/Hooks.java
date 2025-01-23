package com.hooks;

import java.io.IOException;

import com.base.BaseClass;
import com.stepdefinitions.ExtentReportListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks extends BaseClass{

    private ExtentReportListener extentReportListener = new ExtentReportListener();
    @Before
    public void runBeforeTest(Scenario scenario) {
        setUp();
        extentReportListener.startTest(scenario);
    }

    @After
    public void runAfterTest(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            String screenshotPath = captureScreenshot(screenshotName);
            String relPath=System.getProperty("user.dir") + screenshotPath;
            extentReportListener.attachScreenshot(relPath);
        }
        extentReportListener.afterScenario(scenario);
        extentReportListener.afterTest();
        tearDown();
    }
}
