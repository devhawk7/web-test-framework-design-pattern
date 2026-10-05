package com.framework.pages;

import com.framework.config.ConfigReader;
import com.framework.decorators.DecoratingFieldDecorator;
import com.framework.decorators.ElementDecorators;
import com.framework.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Base page. Only initialises the page; waiting/logging/highlighting live in element decorators. */
public abstract class AbstractPage {
    protected final WebDriver driver;
    protected final Log log = Log.forClass(getClass());
    private final WebDriverWait wait;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        int timeout = ConfigReader.getInstance().getInt("explicit.wait", 10);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        PageFactory.initElements(
                new DecoratingFieldDecorator(new AjaxElementLocatorFactory(driver, timeout), driver, wait), this);
    }

    /** Contract for every page: returns true/false and never throws. */
    public final boolean isOpened() {
        try {
            return checkOpened();
        } catch (WebDriverException e) {
            log.debug("{} is not opened: {}", getClass().getSimpleName(), e.getClass().getSimpleName());
            return false;
        }
    }

    protected abstract boolean checkOpened();

    /** For elements found dynamically (not via @FindBy) - gets the same decorators as page fields. */
    protected WebElement decorated(WebElement element, String name) {
        return ElementDecorators.decorate(element, name, driver, wait);
    }
}
