package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.utils.ReusableClass;

public class ProjectInfo {
    private ReusableClass reusable;

    // Locators
    private By projectNameField = By.xpath("//input[@name='projectName']");
    private By streetAddressField = By.xpath("//input[@name='streetAddress']");
    private By cityNameField = By.xpath("//input[@name='cityName']");
    private By addressCountryDrpDwn = By.xpath("//select[@name='addressCountry']");
    private By addessStateDrpDwn= By.xpath("//select[@name='addessState']");
    private By postalCodeField = By.xpath("//input[@placeholder='Postal Code']");
    private By installationDateCalendar = By.xpath("//input[@name='installationDate']");
    private By installlationDate= By.xpath("//td[@data-value='2025-01-14']");
    private By operateDateCalendar = By.xpath("//input[@name='operateDate']");
    private By operateDate= By.xpath("//td[@data-value='2025-01-14']");
    private By nextBtn= By.xpath("//button[@title='NavigatetoPurchaseInformation']");
    

    public ProjectInfo(ReusableClass reusable) {
        this.reusable = reusable;
    }
     // Method to generate dynamic locator
    
    public String getProjectName() {
        String path="projectInfo.json";
        return reusable.getText(reusable.getAttr(path,"projectName"));
    }

    public String getStreetAddress() {
        return reusable.getText(streetAddressField);
    }

    public String getCityName() {
        return reusable.getText(cityNameField);
    }

    public String getAddress() {
        return reusable.getText(addressCountryDrpDwn);
    }

    public String getState() {
        return reusable.getText(addessStateDrpDwn);
    }

    public String getPostalCode() {
        return reusable.getText(postalCodeField);
    }

    public void enterProjectName(String username) {
        reusable.sendKeys(projectNameField, username);
    }

    public void enterStreetAddress(String password) {
        reusable.sendKeys(streetAddressField, password);
    }
    public void enterCityName(String username) {
        reusable.sendKeys(cityNameField, username);
    }
    public void selectAddress(String countryValue) {
        WebElement dropdown = reusable.findElement(addressCountryDrpDwn);
        Select select = new Select(dropdown);
        select.selectByValue(countryValue);
    }
    public void selectState(String stateValue) {
        
        WebElement dropdown = reusable.findElement(addessStateDrpDwn);
        Select select = new Select(dropdown);
        select.selectByValue(stateValue);
    }
    public void enterPostalCode(String username) {
        reusable.sendKeys(postalCodeField, username);
    }
    public void selectInstallationDate() {
        reusable.click(installationDateCalendar);
        reusable.waitForElementVisible(installlationDate);
        reusable.click(installlationDate);
    }
    public void selectOperationDate() {
        reusable.click(operateDateCalendar);
        reusable.click(operateDate);
        reusable.goToNextPage(nextBtn);
    }
    
    
}
