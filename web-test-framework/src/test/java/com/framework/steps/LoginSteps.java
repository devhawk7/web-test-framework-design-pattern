package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.model.User;
import com.framework.pages.InventoryPage;
import com.framework.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("I am on the SauceDemo login page")
    public void openLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.open();
    }

    @When("I login with valid credentials")
    public void loginWithValidCredentials() {
        User user = new User("standard_user", "secret_sauce");
        inventoryPage = loginPage.loginAs(user);
    }

    @When("I login with username {string} and password {string}")
    public void loginWithUsernameAndPassword(String username, String password) {
        User user = new User(username, password);
        inventoryPage = loginPage.loginAs(user);
    }

    @Then("I should be redirected to the inventory page")
    public void verifyInventoryPage() {
        Assert.assertTrue(
                inventoryPage.isOpened(),
                "Inventory page was not opened"
        );
    }

    @Then("I should see the inventory page")
    public void verifyInventoryPageIsDisplayed() {
        Assert.assertTrue(
                inventoryPage.isOpened(),
                "Inventory page was not opened"
        );
    }
}
