package com.framework.decorators;

import com.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Single place that defines the decorator chain: Logging -> Waiting -> Highlighting -> real element. */
public final class ElementDecorators {
    private ElementDecorators() { }

    public static WebElement decorate(WebElement element, String name, WebDriver driver, WebDriverWait wait) {
        ConfigReader config = ConfigReader.getInstance();
        WebElement highlighted = new HighlightingDecorator(element, driver,
                config.getBoolean("highlight.enabled", true), config.getInt("highlight.delay.ms", 250));
        WebElement waiting = new WaitingDecorator(highlighted, wait);
        return new LoggingDecorator(waiting, name);
    }
}
