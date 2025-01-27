package com.hooks;

// import java.io.File;
import java.io.IOException;

// import org.apache.commons.io.FileUtils;

// import org.junit.AfterClass;

import com.aiointegration.ResultUploaderToAIOTest;
import com.base.BaseClass;
// import com.github.javafaker.File;
import com.stepdefinitions.ExtentReportListener;
import io.cucumber.java.After;
// import io.cucumber.java.AfterAll;
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
            // byte[] screenshotBytes = FileUtils.readFileToByteArray(new File(screenshotPath));
            // scenario.attach(screenshotBytes, "image/png", scenario.getName());
            uploadScreenshotToJira(screenshotPath, scenario.getName());
        }
        extentReportListener.afterScenario(scenario);
        extentReportListener.afterTest();
        tearDown();
    }
    private void uploadScreenshotToJira(String screenshotPath, String scenarioName) throws IOException {
      
        ResultUploaderToAIOTest.uploadScreenshotToJira(screenshotPath, scenarioName);
        // String screenshotFolderPath = System.getProperty("user.dir") + "/target/screenshots/";

  }
}
