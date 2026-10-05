package com.framework.driver.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverFactory extends WebDriverFactory {
    @Override
    protected WebDriver createDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions o = new EdgeOptions();
        if (headless) o.addArguments("--headless=new");
        o.addArguments("--window-size=1920,1080");
        return new EdgeDriver(o);
    }
}
