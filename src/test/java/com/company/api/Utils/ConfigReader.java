package com.company.api.Utils;


import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties props = new Properties();

    public static void load(String fileName) {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (is == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }
            props.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file: " + fileName, e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}


