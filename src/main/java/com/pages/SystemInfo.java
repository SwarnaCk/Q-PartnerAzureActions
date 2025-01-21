package com.pages;

import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.utils.ReusableClass;

public class SystemInfo {
    private ReusableClass reusable;

    // Locators
    private By siteId = By.xpath("//input[@name='siteId']");
    private By productDrpDwn = By.xpath("//button[@name='haveProduct']");
    private By productDrpDwnValue = By.xpath("//lightning-base-combobox-item[@data-value='No']");
    private By moduleDrpdwnValue = By.xpath("//lightning-formatted-text[contains(text(),'Qcells DC Solar panel')]/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//lightning-base-combobox-item//span[text()='NO']");
    private By brandDrpDwn = By.xpath("//select[@name='PanelBrand']");
    // private By brandTextBox = By.xpath("//input[@name='PanelBrand']");
    private By powerClassDrpDwn = By.xpath("//select[@name='PanelPowerClass']");
    private By numberOfPanels = By.xpath("//input[@name='NumberOfPanles']");
    private By regNumberField= By.xpath("//input[@name='WifiDongleReNum']");
    private By rackingField= By.xpath("//span[@title='Racking']");
    private By rackingBrandDrpDwn = By.xpath("//select[@name='RackingBrand']");
    private By essProductDrpdwn = By.xpath("//lightning-formatted-text[contains(text(),'ESS Products?')]/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//button");
    private By essProduct = By.xpath("//lightning-formatted-text[contains(text(),'ESS Products?')]/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//lightning-base-combobox-item//span[text()='No']");

    private By nextBtn= By.xpath("//div[@class='btnCls']//button[contains(text(),'Next')]");
    private By productdropdown = By.xpath("//button[@name=\"yesNoPicklist\"]");
    private By solarpanneldropdown = By.xpath("(//button[@name=\"haveProduct\"])[1]");
    private By pvInverterButton = By.xpath("//span[text()=\"PV Inverter\"]/..");
    private By brandDropdownUnderPVInverter = By.xpath("//select[@name=\"PVInvertorBrand\"]");
    private By batteryDropdown = By.xpath("//span[text()=\"Battery\"]");
    private By nextButton = By.xpath("//button[@title=\"NavigatetoSystemInformation\"]");

    public SystemInfo(ReusableClass reusable) {
        this.reusable = reusable;
    }
    /*
     *   public String getSiteId() {
        return reusable.getText(siteIdField);
    }
     */
  

    public String getBrand() {
        String path="systemInfo.json";
        return reusable.getText(reusable.getAttr(path,"brand"));
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
    public void selectPowerClass() {
        WebElement dropdown = reusable.findElement(powerClassDrpDwn);
        Select select = new Select(dropdown);
        select.selectByIndex(2);
        
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
    public void selectProduct() {
        reusable.click(productdropdown);
        reusable.click(productDrpDwnValue);
    }
    public void selectModule() throws InterruptedException{
        reusable.click(solarpanneldropdown);   
        Thread.sleep(2000);    
        reusable.clickUsingJavaScript(moduleDrpdwnValue);
    }
    public void essProduct() {
        // WebElement dropdown = reusable.findElement(pvInverterButton);
        // Select select = new Select(dropdown);
        // select.selectByVisibleText(essProduct);
        reusable.clickUsingJavaScript(essProductDrpdwn);
        reusable.click(essProduct);
    }
    public void clickPVInverter() {
        reusable.click(pvInverterButton);
        
    }
    public void selectBrandUndePVInverter() {
        WebElement dropdown = reusable.findElement(brandDropdownUnderPVInverter);
        Select select = new Select(dropdown);
        select.selectByVisibleText("AP Systems");
    }
    public void clickBattery() {
        reusable.click(batteryDropdown);
        reusable.click(nextButton);
        
    }
}
