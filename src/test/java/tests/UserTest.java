package tests;

import api.UserService;
import client.ApiClient;
import constants.ApiConstants;
import models.response.GetUserByIdResponse;
import models.response.GetUsersResponse;
import models.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class UserTest {
    private static final ApiClient apiclient = new ApiClient();
    private static final UserService userService = apiclient.getUserService();

    @Test
    public void test1() throws IOException {
        int page = 1;
        Response<GetUsersResponse> response = userService
                .getUserList(ApiConstants.getToken(), page)
                .execute();

        Assertions.assertThat(response.isSuccessful()).isTrue();

        GetUsersResponse bodyResponse = response.body();
        Assertions.assertThat(bodyResponse.getPage()).isEqualTo(page);

        List<UserResponse> userData = bodyResponse.getData();
        Assertions.assertThat(!userData.isEmpty()).isTrue();
    }

    @Test
    public void test2() throws IOException {
        int id = 1; // ? выше
        Response<GetUserByIdResponse> response = userService
                .getUserById(ApiConstants.getToken(), id)
                .execute();

        Assertions.assertThat(response.isSuccessful()).isTrue();
    }

    // сделать тесты: проверка количества записей на странице, формат возвращаемого ответа
}
