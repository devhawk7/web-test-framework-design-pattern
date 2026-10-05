package com.framework.decorators;

import com.framework.utils.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Bonus: highlights the element before every action performed on it. */
public class HighlightingDecorator extends WebElementDecorator {
    private static final Log LOG = Log.forClass(HighlightingDecorator.class);
    private final WebDriver driver;
    private final boolean enabled;
    private final int delayMs;

    public HighlightingDecorator(WebElement delegate, WebDriver driver, boolean enabled, int delayMs) {
        super(delegate);
        this.driver = driver;
        this.enabled = enabled;
        this.delayMs = delayMs;
    }

    @Override public void click()                          { highlight(); super.click(); }
    @Override public void submit()                         { highlight(); super.submit(); }
    @Override public void clear()                          { highlight(); super.clear(); }
    @Override public void sendKeys(CharSequence... keys)   { highlight(); super.sendKeys(keys); }
    @Override public String getText()                      { highlight(); return super.getText(); }

    private void highlight() {
        if (!enabled) {
            return;
        }
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.outline='3px solid red';"
                    + "arguments[0].style.backgroundColor='rgba(255,255,0,0.4)';", delegate);
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            LOG.debug("Element could not be highlighted: {}", e.getMessage());   // never break the real action
        }
    }
}
