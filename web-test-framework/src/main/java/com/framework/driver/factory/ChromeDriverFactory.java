package com.framework.driver.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class ChromeDriverFactory extends WebDriverFactory {
    @Override
    protected WebDriver createDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions o = new ChromeOptions();
        if (headless) o.addArguments("--headless=new");
        o.addArguments("--window-size=1920,1080", "--incognito", "--disable-notifications");
        o.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false));
        return new ChromeDriver(o);
    }
}
