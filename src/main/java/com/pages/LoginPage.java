package com.pages;

import org.openqa.selenium.By;

import com.utils.ReusableClass;

public class LoginPage {
    private ReusableClass reusable;

    // Locators
    private By usernameField = By.xpath("//input[@placeholder='Username']");
    private By passwordField = By.xpath("//input[@placeholder='Password']");
    private By loginButton = By.xpath("//span[text()='Log in']");
    private By loggedInIndicator = By.xpath("//a[text()='Home']"); // Update this with the actual selector

    public LoginPage(ReusableClass reusable) {
        this.reusable = reusable;
    }

    public void enterUsername(String username) {
        reusable.sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        reusable.sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        reusable.click(loginButton);
    }

    public boolean isLoggedIn() {
        reusable.waitForElementVisible(loggedInIndicator);
        return reusable.isElementPresent(loggedInIndicator);
    }
}
