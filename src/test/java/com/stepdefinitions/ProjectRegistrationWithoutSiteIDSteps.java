package com.stepdefinitions;

import org.json.simple.JSONObject;
import com.pages.SystemInfo;
import com.utils.ConfigReader;
import com.utils.ReusableClass;
import io.cucumber.java.en.And;


public class ProjectRegistrationWithoutSiteIDSteps {
    private ReusableClass reusable = new ReusableClass();
    private SystemInfo systemInfo = new SystemInfo(reusable);
    private JSONObject jsonObject;

// @And("I select 'No' option in dropdown in system information page")
// public void iSelectNoOptionInDropdownInSystemInformationPage() throws InterruptedException{
//         jsonObject=ConfigReader.readJsonFile("systemInfo.json");
//         jsonObject.get("jsonObject");
//         systemInfo.selectProduct();
//         systemInfo.selectModule();
//         systemInfo.selectBrand((String)jsonObject.get("brand"));
//         systemInfo.selectPowerClass((String)jsonObject.get("powerClass"));
//         systemInfo.selectNumberOfPanels((String)jsonObject.get("numberOfPanels"));
//         systemInfo.essProduct("No");
//         systemInfo.clickPVInverter();


// }

}
