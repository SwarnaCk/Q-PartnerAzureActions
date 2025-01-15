package com.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import com.utils.ReusableClass;
import com.pages.LoginPage;
import com.pages.ProjectInfo;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProductRegistrationSteps {
    private ReusableClass reusable = new ReusableClass();
    private LoginPage loginPage = new LoginPage(reusable);
    private ProjectInfo projectInfo = new ProjectInfo(reusable);

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        reusable.navigateTo("https://qcellsnorthamerica123--dev2.sandbox.my.site.com/qpp/s/login/?startURL=%2Fqpp%2Fs%2F%3Ft%3D1736925342820");
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        String actualTitle = reusable.getPageTitle();
        String expectedTitle = "Home";
        
        Assertions.assertTrue(loginPage.isLoggedIn(), "User should be logged in successfully");
       
        
    }
    @Then("I fill the project information details")
    public void iFillTheProjectInformationDetails() {
        // Write code to fill project information details
        projectInfo.enterProjectName("Test Project");
        projectInfo.enterStreetAddress("123 Test Street");
        projectInfo.enterCityName("Test City");
        projectInfo.selectAddress("CA");
        projectInfo.selectState("MB"); // Assuming ON for Ontario
        projectInfo.enterPostalCode("A1A 1A1");
        
        // For installation and operation dates, you might want to use the current date
        // or a specific date. Here's an example using a specific date:
        projectInfo.selectInstallationDate("2023-07-01");
        projectInfo.selectOperationDate("2023-07-15");
    }
}
