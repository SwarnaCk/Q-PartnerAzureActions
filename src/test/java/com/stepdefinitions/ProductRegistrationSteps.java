package com.stepdefinitions;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import com.utils.ConfigReader;
import com.utils.ReusableClass;
import com.pages.LoginPage;
import com.aiointegration.ResultUploaderToAIOTest;
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
    private final ResultUploaderToAIOTest resultUploader;

    public ProductRegistrationSteps() {
        String aioToken = System.getenv("AIO_TOKEN") != null ? System.getenv("AIO_TOKEN")
                : ConfigReader.loadEnv("AIO_TOKEN");
        String gitToken = System.getenv("GIT_TOKEN") != null ? System.getenv("GIT_TOKEN")
                : ConfigReader.loadEnv("GIT_TOKEN");
        this.resultUploader = new ResultUploaderToAIOTest(aioToken, gitToken);
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        reusable.navigateTo(
                "https://qcellsnorthamerica123--dev2.sandbox.my.site.com/qpp/s/login/?startURL=%2Fqpp%2Fs%2F%3Ft%3D1736925342820");
    }

    @When("I enter username")
    public void iEnterUsername() {
        String username = resultUploader.getGitHubActionVariable("USERNAME");
        loginPage.enterUsername(username);
    }

    @When("I enter password")
    public void iEnterPassword() {
        String password = resultUploader.getGitHubActionVariable("PASSWORD");
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
    public void iFillTheSystemInformationDetails() throws InterruptedException {
        jsonObject = ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.enterSiteId((String) jsonObject.get("siteId"));
        systemInfo.selectProductDropdown();
        Thread.sleep(2000);
        systemInfo.selectPowerclassDropdown((String) jsonObject.get("powerClass"));
        Thread.sleep(2000);
        systemInfo.selectTypeDropdown((String) jsonObject.get("type"));
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.selectProductGenerationDropdown((String) jsonObject.get("productGeneration"));
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.selectModelDropdown((String) jsonObject.get("model"));
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.selectNumberOfPanels((String) jsonObject.get("numberOfPanels"));
        systemInfo.enterRegistrationNumber((String) jsonObject.get("registrationNumber"));
        systemInfo.selectRackingBrand((String) jsonObject.get("rackingBrand"));
    }

    @When("I fill the project information details")
    public void iFillTheProjectInformationDetails() {
        jsonObject = ConfigReader.readJsonFile("projectInfo.json");
        jsonObject.get("jsonObject");

        projectInfo.enterProjectName((String) jsonObject.get("projectName"));
        projectInfo.enterStreetAddress((String) jsonObject.get("streetAddress"));
        projectInfo.enterCityName((String) jsonObject.get("cityName"));
        projectInfo.selectAddress((String) jsonObject.get("address"));
        projectInfo.selectState((String) jsonObject.get("state"));
        projectInfo.enterPostalCode((String) jsonObject.get("postalCode"));

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
        jsonObject = ConfigReader.readJsonFile("projectOwnerInfo.json");
        String fname = (String) jsonObject.get("ownerFName");
        String lname = (String) jsonObject.get("ownerLName");
        projectOwnerInfo.selectOwnerName(fname, lname);
        projectOwnerInfo.selectOwnerEmail((String) jsonObject.get("ownerEmail"));
        projectOwnerInfo.selectOwnerContactNumber((String) jsonObject.get("ownerContactNum"));
        projectOwnerInfo.selectNotes((String) jsonObject.get("notes"));
    }

    @Then("I verify project Registration Details in Project Details Tab")
    public void iVerifyProjectRegistrationDetailsInProjectDetailsTab() {
        JSONObject projectInfoJson = ConfigReader.readJsonFile("projectInfo.json");
        Assert.assertEquals(projectInfo.getProjectName(), (String) projectInfoJson.get("projectName"));
        JSONObject projectOwnerInfoJson = ConfigReader.readJsonFile("projectOwnerInfo.json");
        Assert.assertEquals((String) projectOwnerInfoJson.get("ownerEmail"), projectOwnerInfo.getOwnerEmail());
        Assert.assertEquals((String) projectOwnerInfoJson.get("ownerContactNum"),
                projectOwnerInfo.getOwnerContactNumber());
        Assert.assertEquals((String) projectOwnerInfoJson.get("notes"), projectOwnerInfo.getNotes());
    }

    @Then("I click the submit button")
    public void iClickSubmitButton() throws InterruptedException {
        projectDetails.saveDetails();
    }

    @Then("I verify details of Project in project details section")
    public void iVerifyProjectDetailsInProjectDetailsTable() {
        JSONObject projectInfoJson = ConfigReader.readJsonFile("projectInfo.json");
        String actualProjectName = projectListTab.getProjectName();
        String expectedProjectName = (String) projectInfoJson.get("projectName");
        Assert.assertEquals(expectedProjectName, actualProjectName);
    }

    @When("I click on the download PDF button")
    public void clickDownloadPdfButton() {
        projectListTab.clickDownloadPdf();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("I switch to the PDF tab")
    public void switchToPdfTab() {
        projectListTab.switchToPdfTab();
    }

    @Then("the PDF should contain the project name")
    public void validatePdfContent() throws Exception {
        boolean containsProjectName = projectListTab.validatePdfContent("Test City");
        Assertions.assertTrue(containsProjectName);
    }

    @Then("the PDF should contain the 'Complete System' text")
    public void validateCompleteSystemText() throws Exception {
        boolean containsProjectName = projectListTab.validateCompleteSystemTextContent("Complete System");
        Assertions.assertTrue(containsProjectName);
    }

    @Then("the Certificate should contain the 'Battery' text")
    public void validateBatteryText() throws Exception {
        boolean containsProjectName = projectListTab.validateBatteryText("Battery");
        Assertions.assertTrue(containsProjectName);
    }

    @Then("the PDF should contain Panel Text")
    public void validatePanelText() throws Exception {
        boolean containsProjectName = projectListTab.validatePanelText("Panel");
        Assertions.assertTrue(containsProjectName);
    }

    @Then("I close the PDF tab and switch back")
    public void closePdfTabAndSwitchBack() {
        projectListTab.closePdfTabAndSwitchBack();
    }

    @And("I select 'No' option in dropdown in system information page")
    public void iSelectNoOptionInDropdownInSystemInformationPage() throws Exception {
        jsonObject = ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.selectProductWithNoOption();
        systemInfo.selectModule();
        systemInfo.selectBrand((String) jsonObject.get("brand"));
        systemInfo.selectPowerClass();
        systemInfo.selectNumberOfPanels((String) jsonObject.get("numberOfPanels"));
        systemInfo.essProduct();
        systemInfo.clickPVInverter();
        systemInfo.selectBrandUndePVInverter();
        systemInfo.clickBattery();
    }

    @And("I am not able to see some fields after selecting 'No' option")
    public void somefieldsnotvisible() {
        Assertions.assertFalse(systemInfo.isSiteIDVisible());
        Assertions.assertFalse(systemInfo.isTypeVisible());
        Assertions.assertFalse(systemInfo.isProductGenerationVisible());
        Assertions.assertFalse(systemInfo.isModelsVisible());
        Assertions.assertFalse(systemInfo.isRegistrationNoVisible());
        Assertions.assertFalse(systemInfo.isRackingVisible());
        systemInfo.clickNextBtn();

    }

    @And("I select 'No' in the product availability dropdown")
    public void iSelectNoInTheProductAvailabilityDropdown() {
        systemInfo.selectProductWithNoOption();
        Assertions.assertFalse(systemInfo.isSiteIDVisible());
    }

    @And("I select 'No' in the solar panel dropdown")
    public void iSelectNoInTheSolarPanelDropdown() throws InterruptedException {
        systemInfo.selectModule();
        jsonObject = ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.selectBrand((String) jsonObject.get("brand"));
        systemInfo.selectPowerclassDropdown((String) jsonObject.get("powerClass"));
        systemInfo.selectNumberOfPanels((String) jsonObject.get("numberOfPanels"));
        Assertions.assertFalse(systemInfo.isTypeVisible());
    }

    @And("I select 'No' for ESS product and enable the battery option")
    public void verifyESSProductDropdown() throws InterruptedException {
        // Assertions.assertFalse(systemInfo.isBatteryStatusVisible());
        systemInfo.essProduct();
        Thread.sleep(1000);
        systemInfo.clickPVInverter();
        Thread.sleep(1000);
        systemInfo.selectBrandUndePVInverter();
        Thread.sleep(1000);
        systemInfo.clickBattery();
        Thread.sleep(1000);
        systemInfo.clickBatteryYesOption();
        Thread.sleep(1000);
        systemInfo.batteryBrandSelectDropdown();
        Thread.sleep(1000);
        systemInfo.clickNextBtn();
    }

    @And("I am on the system information page")
    public void systemInformationTextVisible() {
        Assertions.assertTrue(systemInfo.isSystemInformationTextVisible());
    }

    @And("I enter Site Id as 'Yes' option is seelected in product dropdown")
    public void enterSiteId() {
        jsonObject = ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.enterSiteId((String) jsonObject.get("siteId"));
    }

    @And("I fill model, powerclass, type ,product generation as 'Yes' option is selected in solar panel dropdown")
    public void enterAllDetailsUndersolarPanel() throws Exception {
        jsonObject = ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.selectProductDropdown();
        Thread.sleep(2000);
        systemInfo.selectPowerclassDropdown((String) jsonObject.get("powerClass"));
        Thread.sleep(2000);
        systemInfo.selectTypeDropdown((String) jsonObject.get("type"));
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.selectProductGenerationDropdown((String) jsonObject.get("productGeneration"));
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.selectModelDropdown((String) jsonObject.get("model"));
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.selectNumberOfPanels((String) jsonObject.get("numberOfPanels"));
    }

    @And("I fill registration no as'Yes' option is selected in ESS product dropdown")
    public void enterRegistrationNo() {
        jsonObject = ConfigReader.readJsonFile("systemInfo.json");
        jsonObject.get("jsonObject");
        systemInfo.enterRegistrationNumber((String) jsonObject.get("registrationNumber"));
    }

    @And("I cannot verify the battery status")
    public void batteryStatusNotVisible() {
        Assertions.assertFalse(systemInfo.isBatteryStatusVisible());
        systemInfo.clickNextBtn();
    }

    @And("I fill registration no as'No' option is selected in ESS product dropdown")
    public void verifyRegistrationField() throws InterruptedException {
        // Assertions.assertFalse(systemInfo.isBatteryStatusVisible());
        systemInfo.essProduct();
        Thread.sleep(3000);
        systemInfo.clickPVInverter();
        Thread.sleep(3000);
        systemInfo.selectBrandUndePVInverter();
        Thread.sleep(3000);
        systemInfo.clickBattery();
        Thread.sleep(3000);
        systemInfo.clickNextBtn();
    }

    @And("I am not able to see model, powerclass, type ,product generation field as 'No' option is selected in solar panel dropdown")
    public void verifyModelPowerClassTypeField() throws InterruptedException {
        Assertions.assertFalse(systemInfo.isSiteIDVisible());
        Assertions.assertFalse(systemInfo.isTypeVisible());
        Assertions.assertFalse(systemInfo.isProductGenerationVisible());
        Assertions.assertFalse(systemInfo.isModelsVisible());
        Assertions.assertFalse(systemInfo.isRegistrationNoVisible());
        Assertions.assertFalse(systemInfo.isRackingVisible());
    }

    @And("I am not able to fill registration no as 'No' option is selected in ESS product dropdown")
    public void notFillRegistrationNo() throws InterruptedException {
        Assertions.assertFalse(systemInfo.isRegistrationNoVisible());
        Assertions.assertFalse(systemInfo.isRackingVisible());
        systemInfo.clickPVInverter();
        Thread.sleep(3000);
        systemInfo.selectBrandUndePVInverter();
    }

    @And("I select 'No' in the battery status")
    public void selectNoFromBatteryStatus() throws InterruptedException {
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.clickBattery();
        systemInfo.clickNextBtn();
        Thread.sleep(2000);
    }

    @And("I am not able to see model, type, powerclass, product generation field as 'No' option is selected in solar panel dropdown")
    public void verifyTypeModelPowerClassField() throws InterruptedException {
        systemInfo.selectModule();
        systemInfo.selectBrand((String) jsonObject.get("brand"));
        systemInfo.selectPowerClass();
        systemInfo.selectNumberOfPanels((String) jsonObject.get("numberOfPanels"));
        systemInfo.essProduct();
        Assertions.assertFalse(systemInfo.isTypeVisible());
        Assertions.assertFalse(systemInfo.isProductGenerationVisible());
        Assertions.assertFalse(systemInfo.isModelsVisible());
    }

    @And("I select 'Yes' in the battery status")
    public void selectYesFromBatteryStatus() throws InterruptedException {
        reusable.waitForPageLoad();
        Thread.sleep(2000);
        systemInfo.clickBattery();
        systemInfo.clickBatteryCheckboxYes();
        systemInfo.selectBattery((String) jsonObject.get("battery"));
        systemInfo.clickNextBtn();
        Thread.sleep(2000);
    }

    @Then("I verify system information details in Project Details Tab")
    public void iVerifyProjectRegistrationSystemInformationDetailsInProjectDetailsTab() {
        JSONObject systemInfoJson = ConfigReader.readJsonFile("systemInfo.json");
        Assert.assertEquals((String) systemInfoJson.get("siteId"), systemInfo.getSiteId());
        Assert.assertEquals((String) systemInfoJson.get("brand"), systemInfo.getBrand());
        reusable.waitForPageLoad();
        Assert.assertEquals((String) systemInfoJson.get("powerClass"), systemInfo.getPowerClass());
        Assert.assertEquals((String) systemInfoJson.get("battery"), systemInfo.getBattery());
    }
}
