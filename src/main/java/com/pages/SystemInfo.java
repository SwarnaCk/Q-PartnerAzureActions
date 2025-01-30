package com.pages;

// import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
// import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.ReusableClass;

public class SystemInfo {
    private ReusableClass reusable;

    // Locators
    private By siteId = By.xpath("//input[@name='siteId']");
    private By systemInformationText = By.xpath("//div[text()=\"System Information\"]");
    private By productDrpDwn = By.xpath("//button[@name='haveProduct']");
    private By productDrpDwnValue = By.xpath("//lightning-base-combobox-item[@data-value='Yes']");
    private By productDrpDwnValueWithNo = By.xpath("//lightning-base-combobox-item[@data-value='No']");
    private By moduleDrpdwnValue = By.xpath("//lightning-formatted-text[contains(text(),'Qcells DC Solar panel')]/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//lightning-base-combobox-item//span[text()='NO']");
    private By brandDrpDwn = By.xpath("//select[@name='PanelBrand']");
    private By powerClassDrpDwn = By.xpath("//select[@name='PanelPowerClass']");
    private By numberOfPanels = By.xpath("//input[@name='NumberOfPanles']");
    private By regNumberField= By.xpath("//input[@name='WifiDongleReNum']");
    private By rackingField= By.xpath("//span[@title='Racking']");
    private By rackingBrandDrpDwn = By.xpath("//select[@name='RackingBrand']");
    private By essProductDrpdwn = By.xpath("//lightning-formatted-text[contains(text(),'ESS Products?')]/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//button");
    private By essProduct = By.xpath("//lightning-formatted-text[contains(text(),'ESS Products?')]/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//lightning-base-combobox-item//span[text()='No']");
    private By typeDropdown = By.xpath("//select[@name=\"PanelType\"]");
    private By productGeneration = By.xpath("//select[@name=\"PanelGeneration\"]");
    private By nextBtn= By.xpath("//div[@class='btnCls']//button[contains(text(),'Next')]");
    private By productdropdown = By.xpath("//button[@name=\"yesNoPicklist\"]");
    private By solarpanneldropdown = By.xpath("(//button[@name=\"haveProduct\"])[1]");
    private By pvInverterButton = By.xpath("//span[text()=\"PV Inverter\"]/..");
    private By brandDropdownUnderPVInverter = By.xpath("//select[@name=\"PVInvertorBrand\"]");
    private By batteryDropdown = By.xpath("//span[text()=\"Battery\"]");
    private By nextButton = By.xpath("//button[@title=\"NavigatetoSystemInformation\"]");
    private By model = By.xpath("//select[@name=\"PanelModel\"]");
    private By powerClassDropdown = By.xpath("//select[@name=\"PanelPowerClass\"]");
    public By batteryCheckboxYes = By.xpath("//input[@part='checkbox']");
    public By batteryDropDown = By.xpath("//select[@name='BatteryBrand']");
    private By batteryDropdownYes = By.xpath("//span[@class='slds-checkbox_faux']");
    private By batteryBrand = By.xpath("//select[@name='BatteryBrand']");
    // private By powerClassSelection = By.xpath("//select[@name=\"PanelPowerClass\"]/option[@value=\"235\"]");

    private String path = "systemInfo.json";

    public SystemInfo(ReusableClass reusable) {
        this.reusable = reusable;
    }
    
    public String getBrand() {
        return reusable.getText(reusable.getAttr(path,"brand"));
    }

    public void enterSiteId(String siteIdVal) {
        reusable.sendKeys(siteId, siteIdVal);
    }

    public void selectProductDropdown() {
        reusable.click(productDrpDwn);
        reusable.click(productDrpDwnValue);
    }
    public void selectPowerclassDropdown(String options) {
        WebElement dropdown = reusable.findElement(powerClassDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(options);
    }
    public void selectProductGenerationDropdown(String options) {
        reusable.doubleClickOnElement(productGeneration);
        WebElement dropdown = reusable.findElement(productGeneration);
        Select select = new Select(dropdown);
        select.selectByVisibleText(options);
    }
    public void selectTypeDropdown(String options) {
        WebElement dropdown = reusable.findElement(typeDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(options);
    }
    public void selectModelDropdown(String options) {
        WebElement dropdown = reusable.findElement(model);
        Select select = new Select(dropdown);
        select.selectByVisibleText(options);
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
    public void selectProductWithNoOption() {
        reusable.click(productdropdown);
        reusable.click(productDrpDwnValueWithNo);
    }
    
    public void selectModule() throws InterruptedException{
        reusable.click(solarpanneldropdown);   
        Thread.sleep(2000);    
        reusable.clickUsingJavaScript(moduleDrpdwnValue);
    }
    public void essProduct() {
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
        
    }
    public boolean isModelsVisible( ){
        return reusable.isElementLocated(model);
    }
    public boolean isProductGenerationVisible(){
        return reusable.isElementLocated(productGeneration);
    }
    public boolean isRackingVisible(){
        return reusable.isElementLocated(rackingField);
        
    }

    public void clickBatteryYesOption() {
        reusable.click(batteryDropdownYes);
    }
    public void batteryBrandSelectDropdown() {
        WebElement dropdown = reusable.findElement(batteryBrand);
        Select select = new Select(dropdown);
        select.selectByIndex(2);
    }
    public boolean isRegistrationNoVisible(){
        return reusable.isElementLocated(regNumberField);
    }
    public boolean isSiteIDVisible(){
        return reusable.isElementLocated(siteId);
    }
    public boolean isTypeVisible(){
        return reusable.isElementLocated(typeDropdown);
    }
    public void clickNextBtn() {
        reusable.click(nextButton);
    }
    public boolean isSystemInformationTextVisible() {
        return reusable.isElementLocated(systemInformationText);
    }
    public  boolean isBatteryStatusVisible(){
        return reusable.isElementLocated(batteryDropdown);
    }
    public void selectBattery(String options) {
        WebElement dropdown = reusable.findElement(batteryDropDown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(options);
    }
    public void clickBatteryCheckboxYes(){
        JavascriptExecutor js = (JavascriptExecutor) reusable.getDriver();
        js.executeScript("arguments[0].click();", reusable.findElement(batteryCheckboxYes));
    }
    public String getSiteId() {
        return reusable.getText(reusable.getAttr(path, "siteId"));
    }
    public String getPowerClass() {
        return reusable.getText(reusable.getAttr(path, "powerClass"));
    }
    public String getRegistrationNumber() {
        return reusable.getText(reusable.getAttr(path, "registrationNumber"));
    }
    public String getBattery() {
        return reusable.getText(reusable.getAttr(path, "battery"));
    }
}
