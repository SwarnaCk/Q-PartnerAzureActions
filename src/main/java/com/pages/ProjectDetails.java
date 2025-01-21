package com.pages;

import org.openqa.selenium.By;

import com.utils.ReusableClass;

public class ProjectDetails {
    private ReusableClass reusable;

    private By saveProjectBtn = By.xpath("//button[contains(text(),'Save Project')]");
    private By submitProjectBtn = By.xpath("//button[contains(text(),'Submit')]");
    private By cnfrmSubmitBtn=By.xpath("//button[contains(@title,'NavigatetoSystemInformation') and contains(text(),'Submit')]");

    public ProjectDetails(ReusableClass reusable) {
        this.reusable = reusable;
    }
    public void saveDetails()throws InterruptedException {
        reusable.click(saveProjectBtn);
        reusable.click(submitProjectBtn);
        Thread.sleep(2000);
        reusable.click(cnfrmSubmitBtn);
    }
    
}
