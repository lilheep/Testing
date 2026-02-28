package client;

import client.interceptor.ApiKeyInterceptor;
import client.interceptor.BearerInterceptor;
import config.ConfigProvider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiKeyClient {
    private final Retrofit retrofit;
    private final BearerInterceptor bearerInterceptor;

    public ApiKeyClient(String apiKey) {
        bearerInterceptor = new BearerInterceptor();

        OkHttpClient clientForApiKey = new OkHttpClient().newBuilder()
                .addInterceptor(new ApiKeyInterceptor(apiKey))
                .addInterceptor(bearerInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ConfigProvider.getBaseUrl())
                .client(clientForApiKey)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public void setBearerToken(String bearerToken) {
        bearerInterceptor.setBearerToken(bearerToken);
    }

    public <T> T setService(Class<T> service) { return retrofit.create(service); }
}
