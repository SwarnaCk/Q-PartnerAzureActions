package com.stepdefinitions;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;

import com.utils.ConfigReader;
import com.utils.ReusableClass;
import com.pages.LoginPage;
import com.pages.HomePage;
import com.pages.SystemInfo;
import com.pages.ProjectInfo;
import com.pages.ProjectListTab;
import com.pages.PurchaseInfo;
import com.pages.ProjectOwnerInfo;
import com.pages.ProjectDetails;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProductRegistrationSteps {
    
    private JSONObject jsonObject;
    private ReusableClass reusable = new ReusableClass();
    private LoginPage loginPage = new LoginPage(reusable);
    private ProjectInfo projectInfo = new ProjectInfo(reusable);
    private ProjectDetails projectDetails = new ProjectDetails(reusable);
    private PurchaseInfo purchaseInfo = new PurchaseInfo(reusable);
    private HomePage homePage = new HomePage(reusable);
    private SystemInfo systemInfo = new SystemInfo(reusable);
    private ProjectOwnerInfo projectOwnerInfo = new ProjectOwnerInfo(reusable);
    private ProjectListTab projectListTab = new ProjectListTab(reusable);

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
    public void iFillTheSystemInformationDetails()throws InterruptedException {
        jsonObject=ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.enterSiteId((String)jsonObject.get("siteId"));
        systemInfo.selectProductDropdown();
        systemInfo.selectBrand((String)jsonObject.get("brand"));
        Thread.sleep(3000);
        systemInfo.selectPowerClass();
        systemInfo.selectNumberOfPanels((String)jsonObject.get("numberOfPanels"));
        systemInfo.enterRegistrationNumber((String)jsonObject.get("registrationNumber"));
        systemInfo.selectRackingBrand((String)jsonObject.get("rackingBrand"));
    }
    @When("I fill the project information details")
    public void iFillTheProjectInformationDetails() {
        jsonObject=ConfigReader.readJsonFile("projectInfo.json");
        jsonObject.get("jsonObject");
        
        projectInfo.enterProjectName((String)jsonObject.get("projectName"));
        projectInfo.enterStreetAddress((String)jsonObject.get("streetAddress"));
        projectInfo.enterCityName((String)jsonObject.get("cityName"));
        projectInfo.selectAddress((String)jsonObject.get("address"));
        projectInfo.selectState((String)jsonObject.get("state")); 
        projectInfo.enterPostalCode((String)jsonObject.get("postalCode"));
        
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
        jsonObject=ConfigReader.readJsonFile("projectOwnerInfo.json");
        String fname=(String)jsonObject.get("ownerFName");
        String lname=(String)jsonObject.get("ownerLName");
        projectOwnerInfo.selectOwnerName(fname, lname);
        projectOwnerInfo.selectOwnerEmail((String)jsonObject.get("ownerEmail"));
        projectOwnerInfo.selectOwnerContactNumber((String)jsonObject.get("ownerContactNum"));
        projectOwnerInfo.selectNotes((String)jsonObject.get("notes"));
    }

    @Then("I verify project Registration Details in Project Details Tab")
    public void iVerifyProjectRegistrationDetailsInProjectDetailsTab() {

        // Verify Project Information
        JSONObject projectInfoJson = ConfigReader.readJsonFile("projectInfo.json");
        Assertions.assertEquals((String)projectInfoJson.get("projectName"), projectInfo.getProjectName(), "Project name mismatch");
        // Verify System Information
        JSONObject systemInfoJson = ConfigReader.readJsonFile("systemInfo.json");
        Assertions.assertEquals((String)systemInfoJson.get("brand"), systemInfo.getBrand(), "Brand mismatch");
        // Verify Project Owner Information
        JSONObject projectOwnerInfoJson = ConfigReader.readJsonFile("projectOwnerInfo.json");
        Assertions.assertEquals((String)projectOwnerInfoJson.get("ownerEmail"), projectOwnerInfo.getOwnerEmail(), "Owner email mismatch");
        Assertions.assertEquals((String)projectOwnerInfoJson.get("ownerContactNum"), projectOwnerInfo.getOwnerContactNumber(), "Owner contact number mismatch");
        Assertions.assertEquals((String)projectOwnerInfoJson.get("notes"), projectOwnerInfo.getNotes(), "Notes mismatch");
    }

    @Then("I click the submit button")
    public void iClickSubmitButton()throws InterruptedException {
        projectDetails.saveDetails();
    }
    @Then("I verify details of Project in project details section")
    public void iVerifyProjectDetailsInProjectDetailsTable() { 
        JSONObject projectInfoJson = ConfigReader.readJsonFile("projectInfo.json");
        String actualProjectName=projectListTab.getProjectName();
        String expectedProjectName=(String)projectInfoJson.get("projectName");
        Assertions.assertEquals(expectedProjectName, actualProjectName, "Project name mismatch");
    }

    @When("I click on the download PDF button")
    public void clickDownloadPdfButton() {
        projectListTab.clickDownloadPdf();
    }

    @When("I switch to the PDF tab")
    public void switchToPdfTab() {
        projectListTab.switchToPdfTab();
    }

    @Then("the PDF should contain the project name")
    public void validatePdfContent()throws Exception { 
        boolean containsProjectName = projectListTab.validatePdfContent("Test City");
        Assertions.assertTrue(containsProjectName);
    }

    @Then("I close the PDF tab and switch back")
    public void closePdfTabAndSwitchBack() {
        projectListTab.closePdfTabAndSwitchBack();
    }
    @And("I select 'No' option in dropdown in system information page")
public void iSelectNoOptionInDropdownInSystemInformationPage() throws Exception {
        jsonObject=ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.selectProduct();
        systemInfo.selectModule ();
        systemInfo.selectBrand((String)jsonObject.get("brand"));
        systemInfo.selectPowerClass();
        systemInfo.selectNumberOfPanels((String)jsonObject.get("numberOfPanels"));
        systemInfo.essProduct();
        systemInfo.clickPVInverter();
        systemInfo.selectBrandUndePVInverter();
        systemInfo.clickBattery();
}  
}
