package com.company.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvReader {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String getApiKey() {
        String key = System.getProperty("xApiKey");
        if (key == null || key.isEmpty()) {
            key = dotenv.get("X_API_KEY");
        }
        if (key == null || key.isEmpty()) {
            throw new IllegalStateException("API key not found. Set 'xApiKey' system property or 'X_API_KEY' in .env file.");
        }
        return key;
    }
}
