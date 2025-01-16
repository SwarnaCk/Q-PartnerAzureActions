package com.pages;

import org.openqa.selenium.By;


import com.utils.ReusableClass;

public class HomePage {
    private ReusableClass reusable;

    // Locators
    private By projectBtn = By.xpath("//button[text()='Project']");
    private By addNewProject = By.xpath("//a[@title='Register Project']");
    
    public HomePage(ReusableClass reusable) {
        this.reusable = reusable;
    }

    public void selectNewProjectRegistration() {
        reusable.click(projectBtn);
        reusable.click(addNewProject);
        
    }
    
}
