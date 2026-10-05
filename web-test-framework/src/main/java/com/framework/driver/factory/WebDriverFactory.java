package com.framework.driver.factory;

import com.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * PATTERN: Factory Method. The creator defines the workflow (create() - what is common for all browsers),
 * concrete creators decide WHICH driver is instantiated (createDriver()).
 */
public abstract class WebDriverFactory {

    public final WebDriver create(boolean headless) {
        WebDriver driver = createDriver(headless);
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(ConfigReader.getInstance().getInt("page.load.timeout", 30)));
        driver.manage().window().maximize();
        return driver;
    }

    protected abstract WebDriver createDriver(boolean headless);
}
