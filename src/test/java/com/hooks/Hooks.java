package com.hooks;

import java.io.IOException;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.BaseClass;
import com.utils.ScenarioExtractor;
import com.utils.WorkflowUpdateDispatcher;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {

    @Before
    public void runBeforeTest() {
        setUp();
    }

    @After
    public void runAfterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            String base64Screenshot = captureScreenshot(screenshotName);
            scenario.attach(Base64.getDecoder().decode(base64Screenshot), "image/png", screenshotName + ".png");
        }
        tearDown();
    }

    @AfterAll(order = 2)
    public static void getAllScenarioNamesInJSON() throws IOException {
        ScenarioExtractor.getAllScenarios();
    }

    @AfterAll(order = 1)
    public static void updateWorkflowWithScenariosFromJSON() throws IOException {
        WorkflowUpdateDispatcher.updateWorkflowWithAllScenarios();
    }

    private String captureScreenshot(String testName) {
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return Base64.getEncoder().encodeToString(screenshotBytes);
    }
}