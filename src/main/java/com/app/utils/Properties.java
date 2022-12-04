package com.app.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Properties {

    static Dotenv dotenv = Dotenv.load();

    public static String get(String variable) {
        return dotenv.get(variable);
    }
}
