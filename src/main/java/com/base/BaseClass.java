package com.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.RandomDataGenerator;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;
import java.util.HashMap;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ChromeOptions options;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
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
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
