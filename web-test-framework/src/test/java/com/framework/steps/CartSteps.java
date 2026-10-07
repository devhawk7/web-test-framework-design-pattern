package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.model.Product;
import com.framework.pages.CartPage;
import com.framework.pages.InventoryPage;
import com.framework.pages.LoginPage;
import com.framework.service.TestDataCreator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class CartSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private Product product;

    @Given("I am logged in as a standard user")
    public void loginAsStandardUser() {
        product = TestDataCreator.product();

        inventoryPage = new LoginPage(DriverManager.getDriver())
                .open()
                .loginAs(TestDataCreator.validUser());
    }

    @When("I add the test product to the cart")
    public void addTestProductToCart() {
        inventoryPage.addToCart(product);
    }

    @When("I open the shopping cart")
    public void openShoppingCart() {
        cartPage = inventoryPage.openCart();
    }

    @Then("the cart page should be opened")
    public void verifyCartPageIsOpened() {
        Assert.assertTrue(
                cartPage.isOpened(),
                "Cart page should be opened"
        );
    }

    @Then("the cart should contain the test product")
    public void verifyProductIsInCart() {
        Assert.assertEquals(
                cartPage.getItemNames(),
                List.of(product.name()),
                "Cart should contain the test product"
        );
    }

    @Then("the test product price should be correct")
    public void verifyProductPrice() {
        Assert.assertEquals(
                inventoryPage.getPrice(product),
                product.price(),
                "Product price"
        );
    }

    @Then("the cart badge count should be 1")
    public void verifyCartBadgeCount() {
        Assert.assertEquals(
                inventoryPage.getCartBadgeCount(),
                1,
                "Cart badge count"
        );
    }
}