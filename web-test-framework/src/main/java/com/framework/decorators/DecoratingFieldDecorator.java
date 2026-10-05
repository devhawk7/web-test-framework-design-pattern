package com.framework.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;

/** Plugs the element decorators into PageFactory: every @FindBy WebElement field comes back decorated. */
public class DecoratingFieldDecorator extends DefaultFieldDecorator {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public DecoratingFieldDecorator(ElementLocatorFactory factory, WebDriver driver, WebDriverWait wait) {
        super(factory);
        this.driver = driver;
        this.wait = wait;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Object decorated = super.decorate(loader, field);
        if (decorated instanceof WebElement) {
            String name = field.getName().replaceAll("([a-z])([A-Z])", "$1 $2").toLowerCase();
            return ElementDecorators.decorate((WebElement) decorated, name, driver, wait);
        }
        return decorated;
    }
}
