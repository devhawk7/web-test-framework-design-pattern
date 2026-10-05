package com.framework.pages;

import com.framework.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends AbstractPage {
    private static final String ITEM = "//div[@class='inventory_item' and .//div[text()='%s']]";

    @FindBy(css = ".title") private WebElement pageTitle;
    @FindBy(css = ".shopping_cart_link") private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected boolean checkOpened() {
        return driver.getCurrentUrl().contains("inventory.html") && "Products".equals(pageTitle.getText());
    }

    public InventoryPage addToCart(Product product) {
        decorated(driver.findElement(By.xpath(String.format(ITEM, product.name()) + "//button")),
                "add to cart: " + product.name()).click();
        return this;
    }

    public String getPrice(Product product) {
        return decorated(driver.findElement(By.xpath(
                String.format(ITEM, product.name()) + "//div[@class='inventory_item_price']")),
                "price of " + product.name()).getText();
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(By.cssSelector(".shopping_cart_badge"));
        return badges.isEmpty() ? 0 : Integer.parseInt(decorated(badges.get(0), "cart badge").getText());
    }

    public CartPage openCart() {
        cartLink.click();
        return new CartPage(driver);
    }
}
