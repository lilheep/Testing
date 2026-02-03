package org.example;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    private static final Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) {

        String apiKey = dotenv.get("apiKey");
        System.out.println(apiKey);
    }

    public static int sum(int a, int b) {
        return a + b;
    }
}