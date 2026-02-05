package tests;

import api.UserService;
import client.ApiClient;
import constants.ApiConstants;
import data.Names;
import impl.NamesImpl;
import models.response.GetUserByIdResponse;
import models.response.GetUsersResponse;
import models.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;
import util.RandomUtil;

import java.io.IOException;
import java.util.List;

public class UserTest {
    private static final ApiClient apiclient = new ApiClient();
    private static final UserService userService = apiclient.getUserService();
    private static final RandomUtil random = new RandomUtil();

    @Test
    public void test1() throws IOException {
        Response<GetUsersResponse> response = userService
                .getUserList(ApiConstants.getToken())
                .execute();

        Assertions.assertThat(response.isSuccessful()).isTrue();
        GetUsersResponse responseBody = response.body();

        Assertions.assertThat(responseBody.getPage()).isGreaterThan(0);
        Assertions.assertThat(responseBody.getPerPage()).isGreaterThan(0);
        Assertions.assertThat(responseBody.getTotal()).isGreaterThan(0);
        Assertions.assertThat(responseBody.getTotalPages()).isGreaterThan(0);

        List<UserResponse> userData = responseBody.getData();

        for (UserResponse user : userData) {
            Assertions.assertThat(user.getId()).isGreaterThan(0);
            Assertions.assertThat(user.getEmail()).isNotNull();
            Assertions.assertThat(user.getFirstName()).isNotNull();
            Assertions.assertThat(user.getLastName()).isNotNull();
            Assertions.assertThat(user.getAvatar()).isNotNull();
        }

        Assertions.assertThat(responseBody.getMeta()).isNotNull();
        Assertions.assertThat(responseBody.getSupport()).isNotNull();
    }

    @Test
    public void test2() throws IOException {
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
    public void test3() throws IOException {
        int page = 2;
        int perPage = 3;

        Response<GetUsersResponse> response = userService.
                getUserList(ApiConstants.getToken(), page, perPage)
                .execute();

        Assertions.assertThat(response.isSuccessful()).isTrue();

        GetUsersResponse bodyResponse = response.body();

        Assertions.assertThat(bodyResponse.getPage()).isEqualTo(page);
        Assertions.assertThat(bodyResponse.getPerPage()).isEqualTo(perPage);

        List<UserResponse> userData = bodyResponse.getData();
        Assertions.assertThat(userData.size()).isEqualTo(perPage);
    }

    @Test
    public void test4() throws IOException {
        int id = 1;
        Response<GetUserByIdResponse> response = userService
                .getUserById(ApiConstants.getToken(), id)
                .execute();

        Assertions.assertThat(response.isSuccessful()).isTrue();

        GetUserByIdResponse responseBody = response.body();
    }
}
