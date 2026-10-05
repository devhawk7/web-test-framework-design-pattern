package com.framework.driver;

import com.framework.driver.factory.ChromeDriverFactory;
import com.framework.driver.factory.EdgeDriverFactory;
import com.framework.driver.factory.FirefoxDriverFactory;
import com.framework.driver.factory.WebDriverFactory;

/** Maps a browser name to its factory. A new browser = a new constant + a new factory class. */
public enum BrowserType {
    CHROME(new ChromeDriverFactory()),
    FIREFOX(new FirefoxDriverFactory()),
    EDGE(new EdgeDriverFactory());

    private final WebDriverFactory factory;

    BrowserType(WebDriverFactory factory) {
        this.factory = factory;
    }

    public WebDriverFactory factory() {
        return factory;
    }

    public static BrowserType from(String value) {
        try {
            return valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser: " + value + " (use chrome|firefox|edge)");
        }
    }
}
