package com.pages;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;

import com.utils.ConfigReader;
import com.utils.ReusableClass;

public class ProjectListTab {
    private ReusableClass reusable;

    public ProjectListTab(ReusableClass reusable) {
        this.reusable = reusable;
    }
    public By getAttributeName() {
        JSONObject jsonObject=ConfigReader.readJsonFile("projectInfo.json");
        String keyValue=(String)jsonObject.get("projectName");
        return By.xpath("//a[text()='"+ keyValue +"']");
    }
    public By getDownloadBtn() {
        JSONObject jsonObject=ConfigReader.readJsonFile("projectInfo.json");
        String keyValue=(String)jsonObject.get("projectName");
        return By.xpath("//a[text()='"+ keyValue +"']/../..//button[@title='Download']");
    }
    public String getProjectName() {
        return reusable.getText(getAttributeName());
        
    }
    public void clickDownloadPdf() {
        reusable.click(getDownloadBtn());
    }
    public void switchToPdfTab() {
        ArrayList<String> tabs = new ArrayList<>(reusable.getDriver().getWindowHandles());
        reusable.getDriver().switchTo().window(tabs.get(1)); // Switch to the new tab
    }

    public boolean validatePdfContent(String expectedText) {
        // Wait for the PDF to load
        reusable.waitForPageLoad();

        // Get the page source of the PDF viewer
        String pageSource = reusable.getDriver().getPageSource();

        // Check if the expected text is present in the page source
        return pageSource.contains(expectedText);
    }
    public void closePdfTabAndSwitchBack() {
        reusable.getDriver().close(); // Close the current tab (PDF tab)
        ArrayList<String> tabs = new ArrayList<>(reusable.getDriver().getWindowHandles());
        reusable.getDriver().switchTo().window(tabs.get(0)); // Switch back to the original tab
    }
    
}
