package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:api.properties",
                 "system:properties"})
public interface ApiConfig extends Config {
    @Key("baseUrl")
    String baseUrl();
    @Key("email")
    String email();
    @Key("myId")
    String myId();
    @Key("slug")
    String slug();
    @Key("collectionName")
    String collectionName();
    @Key("projectId")
    String projectId();
    @Key("apiKey")
    String apiKey();
}
