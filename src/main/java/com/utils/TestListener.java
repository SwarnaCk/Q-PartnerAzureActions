// package com.utils;

// public class TestListener {
    
// }
package com.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import com.base.BaseTest;

public class TestListener implements ITestListener {

    BaseTest baseTest = new BaseTest();

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            // Capture screenshot on failure
            baseTest.captureScreenshot(result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // You can add logs or additional behavior on test start if needed
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // You can add logs or additional behavior on test success if needed
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Handle skipped tests if needed
    }

    @Override
    public void onFinish(ITestContext context) {
        // After all tests in the suite finish
    }

    @Override
    public void onStart(ITestContext context) {
        // Before all tests in the suite start
    }
}

