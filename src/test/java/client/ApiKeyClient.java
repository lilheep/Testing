package client;

import api.AppUserService;
import api.CollectionService;
import api.ResourceService;
import api.UserService;
import client.interceptor.ApiKeyInterceptor;
import client.interceptor.BearerInterceptor;
import constants.ApiConstants;
import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiKeyClient {
    @Getter
    private final Retrofit retrofit;
    private final BearerInterceptor bearerInterceptor;

    public ApiKeyClient(String apiKey) {
        bearerInterceptor = new BearerInterceptor();

        OkHttpClient clientForApiKey = new OkHttpClient().newBuilder()
                .addInterceptor(new ApiKeyInterceptor(apiKey))
                .addInterceptor(bearerInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.getBaseUrl())
                .client(clientForApiKey)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public void setBearerToken(String bearerToken) {
        bearerInterceptor.setBearerToken(bearerToken);
    }

}
