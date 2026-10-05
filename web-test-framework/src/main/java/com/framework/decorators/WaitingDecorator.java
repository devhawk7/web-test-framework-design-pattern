package com.framework.decorators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Adds explicit waits (clickable / visible) before actions. */
public class WaitingDecorator extends WebElementDecorator {
    private final WebDriverWait wait;

    public WaitingDecorator(WebElement delegate, WebDriverWait wait) {
        super(delegate);
        this.wait = wait;
    }

    @Override public void click()                          { wait.until(ExpectedConditions.elementToBeClickable(delegate)); super.click(); }
    @Override public void clear()                          { waitVisible(); super.clear(); }
    @Override public void sendKeys(CharSequence... keys)   { waitVisible(); super.sendKeys(keys); }
    @Override public String getText()                      { waitVisible(); return super.getText(); }

    private void waitVisible() {
        wait.until(ExpectedConditions.visibilityOf(delegate));
    }
}
