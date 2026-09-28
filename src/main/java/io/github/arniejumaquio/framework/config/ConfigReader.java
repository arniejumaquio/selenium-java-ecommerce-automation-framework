package io.github.arniejumaquio.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String DEFAULT_ENVIRONMENT = "qa";

    static {
        String environment = System.getProperty("env", DEFAULT_ENVIRONMENT);
        load(environment);
    }

    private ConfigReader() {
    }

    private static void load(String environment) {
        String fileName =  environment.toLowerCase() + ".properties";
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new IllegalStateException("Properties file not found: " + fileName);
            }

            properties.load(inputStream);

        } catch (IOException exception) {
            throw new IllegalStateException("Failed to load properties file: " + fileName, exception);
        }
    }


    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }

        String envValue = System.getenv(key.toUpperCase().replace('.', '_'));
        if (envValue != null && !envValue.isBlank()) {
            return envValue.trim();
        }

        String fileValue = properties.getProperty(key);
        if (fileValue != null && !fileValue.isBlank()) {
            return fileValue.trim();
        }

        throw new IllegalArgumentException("Missing key = "+key);
    }


    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }


    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}