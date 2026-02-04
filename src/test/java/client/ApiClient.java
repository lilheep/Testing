package client;

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
    /*
    1. мб сделать интерфейс для реализации сервисов
    2. ? изменить структуру для возврата service
     */
}
