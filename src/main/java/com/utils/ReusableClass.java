package com.utils;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.base.BaseClass;


public class ReusableClass extends BaseClass {
    public void navigateTo(String url) {
        driver.get(url);
    }
    public By getAttr(String path,String key) {
        JSONObject jsonObject=ConfigReader.readJsonFile(path);
        String keyValue=(String)jsonObject.get(key);
        return By.xpath("//div[contains(text(),'" + keyValue + "')]");
    }
    public WebDriver getDriver() {
        return this.driver;
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
    public WebElement findElement(By locator) {
        return waitForElementVisible(locator).findElement(locator);
    }

    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void clickUsingJavaScript(By locator) {
        WebElement element = waitForElementVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        waitForElementClickable(locator).click();
    }

    public void sendKeys(By locator, String text) {
        waitForElementVisible(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void waitForPageLoad() {
        wait.until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    public void goToNextPage(By nextBtn) {
        click(nextBtn);
    }
}
