package com.pages;

import org.openqa.selenium.By;

import com.utils.ReusableClass;

public class ProjectOwnerInfo {
    private ReusableClass reusable;

    // Locators
    private By projectOwnerFname = By.xpath("//input[@name='OwnerfirstName']");
    private By projectOwnerLname = By.xpath("//input[@name='OwnerlastName']");
    private By projectOwnerEmail= By.xpath("//input[@name='OwnerEmail']");
    private By projectOwnerPhone= By.xpath("//input[@name='OwnerPhone']");
    private By projectOwnerNotes= By.xpath("//textarea[@name='OwnerNoted']");
    private By reviewBtn= By.xpath("//button[@title='Review your Project']");
    private String path = "projectOwnerInfo.json";

    public ProjectOwnerInfo(ReusableClass reusable) {
        this.reusable = reusable;
    }

    public String getOwnerEmail() {
        return reusable.getText(reusable.getAttr(path, "ownerEmail"));
    }

    public String getOwnerContactNumber() {
        return reusable.getText(reusable.getAttr(path, "ownerContactNum"));
    }

    public String getNotes() {
        return reusable.getText(reusable.getAttr(path, "notes"));
    }
    
    public void selectOwnerName(String fName,String lName) {
        reusable.sendKeys(projectOwnerFname, fName);
        reusable.sendKeys(projectOwnerLname, lName);
    }
    public void selectOwnerEmail(String email) {
        reusable.sendKeys(projectOwnerEmail, email);
    }
    public void selectOwnerContactNumber(String number) {
        reusable.sendKeys(projectOwnerPhone, number);
    }
    public void selectNotes(String notes) {
        reusable.sendKeys(projectOwnerNotes, notes);
        reusable.goToNextPage(reviewBtn);
    }
    
}
