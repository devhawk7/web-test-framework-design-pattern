package com.framework.decorators;

import com.framework.utils.Log;
import org.openqa.selenium.WebElement;

/** Logs every action performed on the element (ACTION level) and every text read (DEBUG level). */
public class LoggingDecorator extends WebElementDecorator {
    private static final Log LOG = Log.forClass(LoggingDecorator.class);
    private final String name;

    public LoggingDecorator(WebElement delegate, String name) {
        super(delegate);
        this.name = name;
    }

    @Override public void click() {
        LOG.action("Click on '{}'", name);
        super.click();
    }

    @Override public void clear() {
        LOG.action("Clear '{}'", name);
        super.clear();
    }

    @Override public void sendKeys(CharSequence... keys) {
        String typed = name.toLowerCase().contains("password") ? "****" : String.join("", keys);
        LOG.action("Type '{}' into '{}'", typed, name);
        super.sendKeys(keys);
    }

    @Override public String getText() {
        String text = super.getText();
        LOG.debug("Read text of '{}': '{}'", name, text);
        return text;
    }
}
