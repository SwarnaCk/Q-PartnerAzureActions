package com.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.RandomDataGenerator;

import java.io.File;
import java.io.IOException;
// import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebDriverWait waitForLessTime;
    public ChromeOptions options;

    public void setUp() {

        RandomDataGenerator.generateRandomProjectData("projectInfo.json");
        String downloadDir = System.getProperty("user.dir") + "/src/test/resources/pdfData";
        options = new ChromeOptions();

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadDir); // Set the default download directory
        chromePrefs.put("download.prompt_for_download", false); // Don't ask for download confirmation
        chromePrefs.put("download.directory_upgrade", true); // Enable directory upgrade
        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0); // Disable pop-ups
        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1); // Allow multiple
                                                                                                 // downloads
        chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        chromePrefs.put("plugins.always_open_pdf_externally", true);

        options.setExperimentalOption("prefs", chromePrefs);

        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitForLessTime = new WebDriverWait(driver, Duration.ofSeconds(2));

        com.utils.FileUtils.clearDownloadDirectory(downloadDir);
    }

    public static String captureScreenshot(String testName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationPath = "target/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(destinationPath);
        FileUtils.copyFile(screenshot, destination);
        String path = destination.toPath().toAbsolutePath().normalize().toString()
                .replace(System.getProperty("user.dir"), "");
        System.out.println(path);
        return path;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
