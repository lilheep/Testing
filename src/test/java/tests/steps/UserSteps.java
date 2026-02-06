package tests.steps;

import api.UserService;
import client.ApiClient;
import constants.ApiConstants;
import io.qameta.allure.Step;
import models.response.user.GetUserByIdResponse;
import models.response.user.GetUsersResponse;
import models.response.user.UserResponse;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import util.RandomUtil;

import java.io.IOException;
import java.util.List;

public class UserSteps {
    private static final ApiClient apiclient = new ApiClient();
    private static final UserService userService = apiclient.getUserService();
    private final RandomUtil random = new RandomUtil();

    @Step("Getting list users")
    public Response<GetUsersResponse> getUserList() throws IOException {
        return userService.getUserList(ApiConstants.getToken()).execute();
    }

    @Step("Getting list users on page")
    public Response<GetUsersResponse> getUserList(int page) throws  IOException {
        return userService.getUserList(ApiConstants.getToken(), page).execute();
    }

    @Step("Getting list users on page and per page")
    public Response<GetUsersResponse> getUserList(int page, int perPage) throws IOException {
        return userService.getUserList(ApiConstants.getToken(), page, perPage).execute();
    }

    @Step("Getting user by id")
    public Response<GetUserByIdResponse> getUserById(int id) throws IOException {
        return userService.getUserById(ApiConstants.getToken(), id).execute();
    }

    @Step("Generating valid user ID")
    public int generateValidId() {
        return random.generateValidId();
    }

    @Step("Generating password")
    public String generatePassword() {
        return random.generatePassword();
    }

    @Step("Checking success response")
    public void checkResponseIsSuccessful(Response<?> response) {
        Assertions.assertThat(response.isSuccessful())
                .withFailMessage("Incorrect status code: " + response.code())
                .isTrue();
    }

    @Step("Check response body not null")
    public <T> T checkResponseBodyNotNull(Response<T> response) {
        T responseBody = response.body();
        Assertions.assertThat(responseBody)
                .withFailMessage("Response body is null")
                .isNotNull();
        return responseBody;
    }

    @Step("Check list users")
    public void checkListUsers(GetUsersResponse responseBody, int page, int perPage, int total) {
        Assertions.assertThat(responseBody.getPage())
                .withFailMessage("Not equal page")
                .isEqualTo(page);

        Assertions.assertThat(responseBody.getPerPage())
                .withFailMessage("Not equal perPage")
                .isEqualTo(perPage);

        Assertions.assertThat(responseBody.getTotal())
                .withFailMessage("Not equal total users")
                .isEqualTo(total);

        Assertions.assertThat(responseBody.getData().size())
                .withFailMessage("Count users not equal perPage")
                .isEqualTo(perPage);
    }

    @Step("Check user id")
    public void CheckUserById(UserResponse responseBody, int id) {
        Assertions.assertThat(responseBody.getId())
                .withFailMessage("Not equal id")
                .isEqualTo(id);
    }


}
