package com.framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {
    private static final Log LOG = Log.forClass(ScreenshotUtil.class);
    public static final Path DIR = Paths.get("target", "screenshots");

    private ScreenshotUtil() { }

    public static Path capture(WebDriver driver, String testName) {
        try {
            Files.createDirectories(DIR);
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            Path target = DIR.resolve(testName + "_" + ts + ".png");
            Path tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            Files.copy(tmp, target, StandardCopyOption.REPLACE_EXISTING);
            LOG.info("Screenshot saved: {}", target.toAbsolutePath());
            return target;
        } catch (IOException | RuntimeException e) {
            LOG.error("Failed to take screenshot", e);
            return null;
        }
    }
}
