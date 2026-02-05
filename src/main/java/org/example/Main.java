package org.example;

import java.util.Random;

public class Main {
    private final static Random random = new Random();
    private final static String allowedSymbols =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'*+-/=?^_`{|}~";

    public static void main(String[] args) {
        String email = generateEmail("gmail.com");
        System.out.println(email);
    }

    private static String generateEmail(String domain) {
        StringBuilder localPart = new StringBuilder();
            for (int i = 0; i < 13; i++) {
                localPart.append(allowedSymbols.charAt(random.nextInt(allowedSymbols.length() + 1)));
            }
            return localPart + "@" + domain;
    }
}