package constants;

import lombok.Getter;

public final class ApiConstants {
    @Getter
    private static final String baseUrl = "https://reqres.in/";
    @Getter
    private static final String token = getApiToken();

    private static String getApiToken() {
        return System.getProperty("apiKey");
    }
}
