package client;

import api.AppUserService;
import api.ResourceService;
import api.UserService;
import client.interceptor.ApiKeyInterceptor;
import client.interceptor.BearerInterceptor;
import constants.ApiConstants;
import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {
    private final Retrofit retrofit;
    private final BearerInterceptor bearerInterceptor;
    @Getter
    private final UserService userService;
    @Getter
    private final ResourceService resourceService;
    @Getter
    private final AppUserService appUserService;

    public ApiClient(String apiKey) {
        bearerInterceptor = new BearerInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new ApiKeyInterceptor(apiKey))
                .addInterceptor(bearerInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
        resourceService = retrofit.create(ResourceService.class);
        appUserService = retrofit.create(AppUserService.class);
    }

    public void setBearerToken(String bearerToken) {
        bearerInterceptor.setBearerToken(bearerToken);
    }

}
