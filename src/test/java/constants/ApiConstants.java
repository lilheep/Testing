package constants;

import io.github.cdimascio.dotenv.Dotenv;

public final class ApiConstants {
    public static final String baseUrl = "https://reqres.in/";
    public static final String token = getToken();

    private static String getToken() {
        return Dotenv.load().get("apiKey");
    }
}
