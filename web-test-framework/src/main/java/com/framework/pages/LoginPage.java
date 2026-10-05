package com.framework.pages;

import com.framework.config.ConfigReader;
import com.framework.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    @FindBy(id = "user-name")    private WebElement usernameInput;
    @FindBy(id = "password")     private WebElement passwordInput;
    @FindBy(id = "login-button") private WebElement loginButton;
    @FindBy(css = "[data-test='error']") private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        String url = ConfigReader.getInstance().get("base.url");
        log.action("Open login page {}", url);
        driver.get(url);
        return this;
    }

    public InventoryPage loginAs(User user) {
        submit(user);
        return new InventoryPage(driver);
    }

    /** Login that is expected to fail; stays on the page. */
    public LoginPage loginExpectingError(User user) {
        submit(user);
        return this;
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    private void submit(User user) {
        usernameInput.sendKeys(user.username());
        passwordInput.sendKeys(user.password());
        loginButton.click();
    }

    @Override
    protected boolean checkOpened() {
        return loginButton.isDisplayed();
    }
}
