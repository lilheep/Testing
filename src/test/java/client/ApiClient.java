package client;

import api.AppUserService;
import api.ResourceService;
import api.UserService;
import constants.ApiConstants;
import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiConstants.getBaseUrl())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    @Getter
    private final UserService userService = retrofit.create(UserService.class);

    @Getter
    private final ResourceService resourceService = retrofit.create(ResourceService.class);

    @Getter
    private final AppUserService appUserService = retrofit.create(AppUserService.class);
}
