package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.utils.ReusableClass;

public class PurchaseInfo {
    private ReusableClass reusable;

    // Locators
    private By purchasingDrpDwn = By.xpath("//select[@name='PurchaseClass']");
    private By inputChannelField= By.xpath("//input[@placeholder='Distributors...']");
    private By channelOptions= By.xpath("//span[text()='demock']");

    private By nextBtn= By.xpath("//button[@title='Navigate to Owner Information']");

    public PurchaseInfo(ReusableClass reusable) {
        this.reusable = reusable;
    }

    
    public void selectPurchasingChannel(String channelOptions) {
        WebElement dropdown = reusable.findElement(purchasingDrpDwn);
        Select select = new Select(dropdown);
        select.selectByValue(channelOptions);
    }
    
    public void selectNameOfChannel() {
        reusable.sendKeys(inputChannelField, "demo");
        reusable.waitForElementVisible(channelOptions);
        reusable.click(channelOptions);
        reusable.goToNextPage(nextBtn);
    }
    
}
