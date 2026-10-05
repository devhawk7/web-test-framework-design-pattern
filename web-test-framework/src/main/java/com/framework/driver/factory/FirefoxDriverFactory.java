package com.framework.driver.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory extends WebDriverFactory {
    @Override
    protected WebDriver createDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions o = new FirefoxOptions();
        if (headless) o.addArguments("-headless");
        return new FirefoxDriver(o);
    }
}
