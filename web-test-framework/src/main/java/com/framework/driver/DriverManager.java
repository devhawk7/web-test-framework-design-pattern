package com.framework.driver;

import com.framework.config.ConfigReader;
import com.framework.utils.Log;
import org.openqa.selenium.WebDriver;

/** Responsible ONLY for the per-thread driver lifecycle. Creation is delegated to WebDriverFactory. */
public final class DriverManager {
    private static final Log LOG = Log.forClass(DriverManager.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() { }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            ConfigReader config = ConfigReader.getInstance();
            BrowserType type = BrowserType.from(config.get("browser", "chrome"));
            boolean headless = config.getBoolean("headless", false);
            LOG.info("Starting {} (headless={})", type, headless);
            DRIVER.set(type.factory().create(headless));
        }
        return DRIVER.get();
    }

    /** Returns the current driver without creating one (may be null). */
    public static WebDriver peek() {
        return DRIVER.get();
    }

    public static void quit() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            LOG.info("Closing browser");
            driver.quit();
            DRIVER.remove();
        }
    }
}
