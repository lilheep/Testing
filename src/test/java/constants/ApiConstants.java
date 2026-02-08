package constants;

import lombok.Getter;

public final class ApiConstants {
    @Getter
    private static final String baseUrl = "https://reqres.in/";

    public static String getToken() {
        String token = System.getProperty("apiKey");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null");
        }

        return token;
    }


}
