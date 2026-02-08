package constants;

import lombok.Getter;

public final class ApiConstants {
    @Getter
    private static final String baseUrl = "https://reqres.in/";
    @Getter
    private static final String token = getToken();

    private static String getToken() {
        return System.getProperty("apiKey");
    }
}
