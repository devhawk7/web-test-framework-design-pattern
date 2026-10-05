package com.framework.listeners;

import com.codeborne.selenide.WebDriverRunner;
import com.framework.driver.DriverManager;
import com.framework.utils.Log;
import com.framework.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Log LOG = Log.forClass(TestListener.class);

    @Override public void onTestStart(ITestResult r)   { LOG.info("=== START: {} ===", name(r)); }
    @Override public void onTestSuccess(ITestResult r) { LOG.info("=== PASSED: {} ===", name(r)); }
    @Override public void onTestSkipped(ITestResult r) { LOG.warn("=== SKIPPED: {} ===", name(r)); }

    @Override
    public void onTestFailure(ITestResult r) {
        LOG.error("=== FAILED: {} === {}", name(r), String.valueOf(r.getThrowable()));
        WebDriver driver = DriverManager.peek();
        if (driver == null && WebDriverRunner.hasWebDriverStarted()) {   // Selenide-based tests
            driver = WebDriverRunner.getWebDriver();
        }
        if (driver != null) {
            ScreenshotUtil.capture(driver, r.getTestClass().getRealClass().getSimpleName() + "_" + r.getName());
        } else {
            LOG.warn("No active browser - screenshot skipped");
        }
    }

    private String name(ITestResult r) {
        return r.getTestClass().getRealClass().getSimpleName() + "." + r.getName();
    }
}
