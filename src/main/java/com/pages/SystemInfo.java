package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.utils.ReusableClass;

public class SystemInfo {
    private ReusableClass reusable;

    // Locators
    private By siteId = By.xpath("//input[@name='siteId']");
    private By productDrpDwn = By.xpath("//button[@name='haveProduct']");
    private By productDrpDwnValue = By.xpath("//lightning-base-combobox-item[@data-value='No']");
    private By brandDrpDwn = By.xpath("//select[@name='PanelBrand']");
    private By powerClassDrpDwn = By.xpath("//select[@name='PanelPowerClass']");
    private By numberOfPanels = By.xpath("//input[@name='NumberOfPanles']");
    private By regNumberField= By.xpath("//input[@name='WifiDongleReNum']");
    private By rackingField= By.xpath("//span[@title='Racking']");
    private By rackingBrandDrpDwn = By.xpath("//select[@name='RackingBrand']");

    private By nextBtn= By.xpath("//div[@class='btnCls']//button[contains(text(),'Next')]");

    public SystemInfo(ReusableClass reusable) {
        this.reusable = reusable;
    }

    public void enterSiteId(String siteIdVal) {
        reusable.sendKeys(siteId, siteIdVal);
    }

    public void selectProductDropdown() {
        reusable.click(productDrpDwn);
        reusable.click(productDrpDwnValue);
    }
    public void selectBrand(String options) {
        WebElement dropdown = reusable.findElement(brandDrpDwn);
        Select select = new Select(dropdown);
        select.selectByVisibleText(options);
    }
    public void selectPowerClass(String options) {
        WebElement dropdown = reusable.findElement(powerClassDrpDwn);
        Select select = new Select(dropdown);
        select.selectByVisibleText(options);
    }
    public void selectNumberOfPanels(String number) {
        
        reusable.sendKeys(numberOfPanels, number);
    }
    public void enterRegistrationNumber(String regNum) {
        reusable.sendKeys(regNumberField, regNum);
    }
    public void selectRackingBrand(String options) {
        reusable.click(rackingField);
        WebElement dropdown = reusable.findElement(rackingBrandDrpDwn);
        Select select = new Select(dropdown);
        select.selectByValue(options);
        reusable.goToNextPage(nextBtn);
    }
    
}
