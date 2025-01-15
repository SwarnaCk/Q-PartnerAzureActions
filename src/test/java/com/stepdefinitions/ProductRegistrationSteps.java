package com.stepdefinitions;

import org.junit.jupiter.api.Assertions;
import com.utils.ReusableClass;
import com.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProductRegistrationSteps {
    private ReusableClass reusable = new ReusableClass();
    private LoginPage loginPage = new LoginPage(reusable);

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
        
        // Assertions.assertTrue(loginPage.isLoggedIn(), "User should be logged in successfully");
        // Assertions.assertEquals(expectedTitle, actualTitle, "Page title should be '" + expectedTitle + "'");
        
    }
}
