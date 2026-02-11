package constants;

import lombok.Getter;
import lombok.Setter;

public final class ApiConstants {
    @Getter
    private static final String baseUrl = "https://reqres.in/";

    @Getter
    private static final String email = "andrey.arkhipov.2006@mail.ru";

    @Getter
    @Setter
    private static String tokenApp;

    @Getter
    @Setter
    private static String sessionToken;

    @Getter
    private static final String projectId = "3156";

    public static String getToken() {
        String token = System.getProperty("apiKey");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null");
        }
        return token;
    }


}
