package com.framework.tests;

import com.framework.model.Product;
import com.framework.pages.CartPage;
import com.framework.pages.InventoryPage;
import com.framework.pages.LoginPage;
import com.framework.service.TestDataCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Added product appears in the cart")
    public void addedProductIsInCart() {
        Product product = TestDataCreator.product();
        InventoryPage inventory = new LoginPage(driver).open().loginAs(TestDataCreator.validUser());
        inventory.addToCart(product);
        CartPage cart = inventory.openCart();
        Assert.assertTrue(cart.isOpened(), "Cart page should be opened");
        Assert.assertEquals(cart.getItemNames(), List.of(product.name()));
    }

    @Test(groups = "regression", description = "Cart badge and product price are correct")
    public void badgeAndPriceAreCorrect() {
        Product product = TestDataCreator.product();
        InventoryPage inventory = new LoginPage(driver).open().loginAs(TestDataCreator.validUser());
        Assert.assertEquals(inventory.getPrice(product), product.price(), "Product price");
        inventory.addToCart(product);
        Assert.assertEquals(inventory.getCartBadgeCount(), 1, "Cart badge count");
    }
}
