// package com.stepdefinitions;

// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.ExtentTest;
// import io.cucumber.java.Scenario;
// import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// import org.testng.annotations.BeforeTest;
// import org.testng.annotations.AfterTest;
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.AfterMethod;

// import java.io.File;
// import java.io.IOException;

// import org.apache.commons.io.FileUtils;
// import org.openqa.selenium.OutputType;
// import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.WebDriver;


// public class ExtentReportListener {
//     private static ExtentReports extent;
//     private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
//     private WebDriver driver;

//     @BeforeTest
//     public void beforeScenario() {
//         ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/extent-report.html");
//         extent = new ExtentReports();
//         extent.attachReporter(htmlReporter);
//     }

//     @BeforeMethod
//     public void startTest(Scenario scenario) {
//         testReport.set(extent.createTest(scenario.getName()));
//         scenario.attach("Current Test", "text/plain", scenario.getName());
//     }
//     public static ExtentReports getExtent() {
//         return extent;
//     }

//     @AfterMethod
//     public void afterScenario(Scenario scenario)throws IOException {
//         if (scenario.isFailed()) {
//             String screenshotPath = captureScreenshot(scenario.getName());
//             testReport.get().fail(scenario.getName()).addScreenCaptureFromPath(screenshotPath);
//         } else {
//             testReport.get().pass("Test passed");
//         }
//     }
//     @AfterTest
//     public void afterTest() {
//         extent.flush();
//     }

//     public String captureScreenshot(String scenarioName)throws IOException {
//         TakesScreenshot screenshot = (TakesScreenshot) driver;
//         byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
//         String screenshotPath = "target/screenshots/" + scenarioName + ".png";
//         File screenshotFile = new File(screenshotPath);
//         FileUtils.writeByteArrayToFile(screenshotFile, screenshotBytes);
//         return screenshotPath;
//     }
    
// }
