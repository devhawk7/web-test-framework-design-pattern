package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {
    @FindBy(css = ".inventory_item_name") private List<WebElement> itemNames;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected boolean checkOpened() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    public List<String> getItemNames() {
        return itemNames.stream().map(WebElement::getText).toList();
    }
}
