package client;

import api.AppUserService;
import client.interceptor.BearerInterceptor;
import client.interceptor.ReqresEnvInterceptor;
import constants.ApiConstants;
import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiEnvClient {
    private final Retrofit retrofit;
    private final BearerInterceptor bearerInterceptor;

    @Getter
    private final AppUserService appUserService;

    public ApiEnvClient(String apiEnvKey) {
        bearerInterceptor = new BearerInterceptor();

        OkHttpClient clientForEnvKey = new OkHttpClient().newBuilder()
                .addInterceptor(new ReqresEnvInterceptor(apiEnvKey))
                .addInterceptor(bearerInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(clientForEnvKey)
                .baseUrl(ApiConstants.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        appUserService = retrofit.create(AppUserService.class);
    }

    public void setBearerToken(String bearerToken) { bearerInterceptor.setBearerToken(bearerToken); };
}
