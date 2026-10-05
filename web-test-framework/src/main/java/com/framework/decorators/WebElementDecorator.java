package com.framework.decorators;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

import java.util.List;

/**
 * PATTERN: Decorator (base). Implements WebElement and delegates everything to the wrapped element,
 * so concrete decorators override only the behaviour they add. Any decorator is substitutable for WebElement (LSP).
 */
public abstract class WebElementDecorator implements WebElement, WrapsElement {
    protected final WebElement delegate;

    protected WebElementDecorator(WebElement delegate) {
        this.delegate = delegate;
    }

    @Override public WebElement getWrappedElement()               { return delegate; }
    @Override public void click()                                 { delegate.click(); }
    @Override public void submit()                                { delegate.submit(); }
    @Override public void sendKeys(CharSequence... keysToSend)    { delegate.sendKeys(keysToSend); }
    @Override public void clear()                                 { delegate.clear(); }
    @Override public String getTagName()                          { return delegate.getTagName(); }
    @Override public String getDomProperty(String name)           { return delegate.getDomProperty(name); }
    @Override public String getDomAttribute(String name)          { return delegate.getDomAttribute(name); }
    @Override public String getAttribute(String name)             { return delegate.getAttribute(name); }
    @Override public boolean isSelected()                         { return delegate.isSelected(); }
    @Override public boolean isEnabled()                          { return delegate.isEnabled(); }
    @Override public String getText()                             { return delegate.getText(); }
    @Override public List<WebElement> findElements(By by)         { return delegate.findElements(by); }
    @Override public WebElement findElement(By by)                { return delegate.findElement(by); }
    @Override public SearchContext getShadowRoot()                { return delegate.getShadowRoot(); }
    @Override public boolean isDisplayed()                        { return delegate.isDisplayed(); }
    @Override public Point getLocation()                          { return delegate.getLocation(); }
    @Override public Dimension getSize()                          { return delegate.getSize(); }
    @Override public Rectangle getRect()                          { return delegate.getRect(); }
    @Override public String getCssValue(String propertyName)      { return delegate.getCssValue(propertyName); }
    @Override public String getAccessibleName()                   { return delegate.getAccessibleName(); }
    @Override public String getAriaRole()                         { return delegate.getAriaRole(); }
    @Override public <X> X getScreenshotAs(OutputType<X> target)  { return delegate.getScreenshotAs(target); }
}
