package com.stepdefinitions;


import org.json.simple.JSONObject;

import com.pages.LoginPage;
import com.pages.SystemInfo;
import com.utils.ConfigReader;
import com.utils.ReusableClass;

import io.cucumber.java.en.And;


public class ProjectRegistrationWithoutSiteIDSteps {
    ReusableClass reusable = new ReusableClass();
    LoginPage loginPage= new LoginPage(reusable);
    SystemInfo systemInfo = new SystemInfo(reusable);
    private JSONObject jsonObject;

@And("I select 'No' option in dropdown in system information page")
public void iSelectNoOptionInDropdownInSystemInformationPage() {
        jsonObject=ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.selectProduct("No");
        systemInfo.selectModule("No");
        systemInfo.selectBrand((String)jsonObject.get("brand"));
        systemInfo.selectPowerClass((String)jsonObject.get("powerClass"));
        systemInfo.selectNumberOfPanels((String)jsonObject.get("numberOfPanels"));
        // systemInfo.enterRegistrationNumber((String)jsonObject.get("registrationNumber"));
        // systemInfo.selectRackingBrand((String)jsonObject.get("rackingBrand"));
        systemInfo.essProduct("No");
        
}

}
