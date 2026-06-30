package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManagerGPT {

    private static final Properties prop = new Properties();
    private static String path;

    // Private constructor
    private ConfigManagerGPT() {
    }

    static {

        String env = System.getProperty("env", "qat").trim().toLowerCase();

        System.out.println("Selected Environment : " + env);

        switch (env) {

            case "dev":
                path = "config/config.dev.properties";
                break;

            case "qat":
                path = "config/config.qat.properties";
                break;

            case "uat":
                path = "config/config.uat.properties";
                break;

            default:
                System.out.println("Invalid environment. Loading QAT by default.");
                path = "config/config.qat.properties";
                break;
        }

        System.out.println("Loading Config File : " + path);

        try (InputStream input = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path)) {

            if (input == null) {
                throw new RuntimeException("Cannot find configuration file: " + path);
            }

            prop.load(input);

            System.out.println("Base URL : " + prop.getProperty("baseUrl"));

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + path, e);
        }
    }

    public static String getProperty(String key) {

        String value = prop.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in " + path);
        }

        return value.trim();
    }
}