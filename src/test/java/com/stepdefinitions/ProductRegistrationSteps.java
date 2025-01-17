package com.stepdefinitions;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;

import com.utils.ConfigReader;
import com.utils.ReusableClass;
import com.pages.LoginPage;
import com.pages.HomePage;
import com.pages.SystemInfo;
import com.pages.ProjectInfo;
import com.pages.PurchaseInfo;
import com.pages.ProjectOwnerInfo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProductRegistrationSteps {
    private JSONObject jsonObject;
    private ReusableClass reusable = new ReusableClass();
    private LoginPage loginPage = new LoginPage(reusable);
    private ProjectInfo projectInfo = new ProjectInfo(reusable);
    private PurchaseInfo purchaseInfo = new PurchaseInfo(reusable);
    private HomePage homePage = new HomePage(reusable);
    private SystemInfo systemInfo = new SystemInfo(reusable);
    private ProjectOwnerInfo projectOwnerInfo = new ProjectOwnerInfo(reusable);

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        reusable.navigateTo("https://qcellsnorthamerica123--dev2.sandbox.my.site.com/qpp/s/login/?startURL=%2Fqpp%2Fs%2F%3Ft%3D1736925342820");
    }

    @When("I enter username")
    public void iEnterUsername() {
        String username=ConfigReader.getProperty("username");
        loginPage.enterUsername(username);
    }

    @When("I enter password")
    public void iEnterPassword() {
        String password=ConfigReader.getProperty("password");
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assertions.assertTrue(loginPage.isLoggedIn(), "User should be logged in successfully");
    }
    @When("I click on project Registration Tab")
    public void iClickOnProjectRegTab() {
        homePage.selectNewProjectRegistration();
    }
    @When("I fill the system information details")
    public void iFillTheSystemInformationDetails() {
        jsonObject=ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.enterSiteId((String)jsonObject.get("siteId"));
        systemInfo.selectProductDropdown();
        systemInfo.selectBrand((String)jsonObject.get("brand"));
        systemInfo.selectPowerClass((String)jsonObject.get("powerClass"));
        systemInfo.selectNumberOfPanels((String)jsonObject.get("numberOfPanels"));
        systemInfo.enterRegistrationNumber((String)jsonObject.get("registrationNumber"));
        systemInfo.selectRackingBrand((String)jsonObject.get("rackingBrand"));
    }
    @When("I fill the project information details")
    public void iFillTheProjectInformationDetails() {
        jsonObject=ConfigReader.readJsonFile("projectInfo.json");
        jsonObject.get("jsonObject");
        projectInfo.enterProjectName("projectName");
        projectInfo.enterStreetAddress("streetaddress");
        projectInfo.enterCityName("cityname");
        projectInfo.selectAddress("address");
        projectInfo.selectState("state"); 
        projectInfo.enterPostalCode("postalcode");
        
        projectInfo.selectInstallationDate();
        projectInfo.selectOperationDate();
        

    }
    @When("I fill the purchasing information details")
    public void iFillThePurchasingInformationDetails() {
        purchaseInfo.selectPurchasingChannel("Distributor");
        purchaseInfo.selectNameOfChannel();
    }

    @When("I fill the project owner information details")
    public void iFillTheProjectOwnerInformationDetails() {
        projectOwnerInfo.selectOwnerName("John", "Doe");
        projectOwnerInfo.selectOwnerEmail("johndoe@example.com");
        projectOwnerInfo.selectOwnerContactNumber("1234567890");
        projectOwnerInfo.selectNotes("Test Notes");
    }
    
}
