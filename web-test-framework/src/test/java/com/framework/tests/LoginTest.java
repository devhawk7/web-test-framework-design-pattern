package com.framework.tests;

import com.framework.config.ConfigReader;
import com.framework.pages.InventoryPage;
import com.framework.pages.LoginPage;
import com.framework.service.TestDataCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Valid user can log in")
    public void validUserCanLogIn() {
        InventoryPage page = new LoginPage(driver).open().loginAs(TestDataCreator.validUser());
        Assert.assertTrue(page.isOpened(), "Inventory page should be opened after login");
    }

    @Test(groups = "regression", description = "Locked user sees error")
    public void lockedUserSeesError() {
        LoginPage page = new LoginPage(driver).open().loginExpectingError(TestDataCreator.lockedUser());
        Assert.assertTrue(page.getErrorMessage().contains(ConfigReader.getInstance().get("error.locked")),
                "Locked-out error message expected");
    }
}
