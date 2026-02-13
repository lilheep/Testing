package constants;

import lombok.Getter;
import lombok.Setter;

public final class ApiConstants {
    @Getter
    private static final String baseUrl = "https://reqres.in/";

    @Getter
    private static final String email = "andrey.arkhipov.2006@mail.ru";

    @Getter
    private static final String myId = "54e08546-93b4-4ec2-b3fd-95c01b286458";

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
