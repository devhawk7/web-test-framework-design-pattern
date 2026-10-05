package com.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PATTERN: Singleton (initialization-on-demand holder - lazy and thread-safe).
 * Loads config/{env}.properties once; any key can be overridden with -Dkey=value.
 */
public final class ConfigReader {
    private final Properties props = new Properties();

    private ConfigReader() {
        String env = System.getProperty("env", "qa");
        String path = "config/" + env + ".properties";
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("Environment file not found on classpath: " + path);
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read " + path, e);
        }
    }

    private static final class Holder {
        private static final ConfigReader INSTANCE = new ConfigReader();
    }

    public static ConfigReader getInstance() {
        return Holder.INSTANCE;
    }

    public String get(String key) {
        String value = System.getProperty(key);
        return value != null ? value : props.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        String value = get(key);
        return value != null ? value : defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        String value = get(key);
        return value != null ? Integer.parseInt(value.trim()) : defaultValue;
    }
}
