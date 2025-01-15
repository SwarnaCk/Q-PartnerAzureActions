package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.base.BaseClass;

public class ReusableClass extends BaseClass {
    public void navigateTo(String url) {
        setUp();
        driver.get(url);
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
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
    
}
