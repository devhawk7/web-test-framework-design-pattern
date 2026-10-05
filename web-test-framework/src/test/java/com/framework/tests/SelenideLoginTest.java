package com.framework.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.framework.config.ConfigReader;
import com.framework.service.TestDataCreator;
import com.framework.utils.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

/** Bonus: the same login scenario written with Selenide (with element highlighting). */
public class SelenideLoginTest {
    private static final Log LOG = Log.forClass(SelenideLoginTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Configuration.browser = ConfigReader.getInstance().get("browser", "chrome");
        Configuration.headless = ConfigReader.getInstance().getBoolean("headless", false);
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = ConfigReader.getInstance().getInt("explicit.wait", 10) * 1000L;
        Configuration.reportsFolder = "target/screenshots";   // Selenide's own failure screenshots go to the archived folder
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeWebDriver();
    }

    @Test(groups = {"smoke", "regression"}, description = "Selenide: valid user can log in")
    public void validUserCanLogIn() {
        var user = TestDataCreator.validUser();
        LOG.action("Open {}", ConfigReader.getInstance().get("base.url"));
        open(ConfigReader.getInstance().get("base.url"));
        LOG.action("Type username '{}'", user.username());
        highlight($("#user-name")).setValue(user.username());
        LOG.action("Type password");
        highlight($("#password")).setValue(user.password());
        LOG.action("Click Login");
        highlight($("#login-button")).click();
        highlight($(".title")).shouldHave(text("Products"));
    }

    private SelenideElement highlight(SelenideElement element) {
        element.shouldBe(visible);
        executeJavaScript("arguments[0].style.outline='3px solid red';"
                + "arguments[0].style.backgroundColor='rgba(255,255,0,0.4)';", element);
        return element;
    }
}
