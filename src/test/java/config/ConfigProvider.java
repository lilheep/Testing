package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    private static final ApiConfig config =
            ConfigFactory.create(ApiConfig.class);

    public static String getBaseUrl() {
        return config.baseUrl();
    }

    public static String getEmail() {
        return config.email();
    }

    public static String getMyId() {
        return config.myId();
    }

    public static String getSlug() {
        return config.slug();
    }

    public static String getCollectionName() {
        return config.collectionName();
    }

    public static String getProjectId() {
        return config.projectId();
    }

    public static String getToken() {
        String token = config.apiKey();

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null");
        }
        return token;
    }
}
