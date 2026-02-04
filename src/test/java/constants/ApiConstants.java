package constants;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

public final class ApiConstants {
    @Getter
    private static final String baseUrl = "https://reqres.in/";
    @Getter
    private static final String token = getTokenOnEnv();

    private static String getTokenOnEnv() {
        return Dotenv.load().get("apiKey");
    }
}
