package steps;

import api.UserService;
import client.ApiKeyClient;
import config.ConfigProvider;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import models.request.user.RegisterOrLoginUserRequest;
import models.request.user.UpdateUserRequest;
import models.response.user.*;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import tests.BaseTest;
import util.RandomUtil;


public class UserSteps {
    private final BaseTest baseTest;
    private final ApiKeyClient apiKeyClient;
    private final UserService userService;
    private final RandomUtil random = new RandomUtil();

    public UserSteps(BaseTest baseTest) {
        this.baseTest = baseTest;
        apiKeyClient = new ApiKeyClient(getToken());
        userService = apiKeyClient.setService(UserService.class);
    }

    private String getToken() {
        return ConfigProvider.getToken();
    }

    @Step("Getting list users")
    @SneakyThrows
    public Response<GetUsersResponse> getUserList() {
        return userService.getUserList().execute();
    }

    @Step("Getting list users on page")
    @SneakyThrows
    public Response<GetUsersResponse> getUserList(int page) {
        return userService.getUserList(page).execute();
    }

    @Step("Getting list users on page and per page")
    @SneakyThrows
    public Response<GetUsersResponse> getUserList(int page, int perPage) {
        return userService.getUserList(page, perPage).execute();
    }

    @Step("Getting user by id")
    @SneakyThrows
    public Response<GetUserByIdResponse> getUserById(int id) {
        return userService.getUserById(id).execute();
    }

    @Step("Registration user")
    @SneakyThrows
    public Response<RegisterUserResponse> registerUser(String email, String password) {
        return userService
                .registerUser(new RegisterOrLoginUserRequest(email, password))
                .execute();
    }

    @Step("Logout user")
    @SneakyThrows
    public Response<Void> logoutUser() {
        return userService.logoutUser().execute();
    }

    @Step("Login user")
    @SneakyThrows
    public Response<LoginUserResponse> loginUser(String email, String password) {
        return userService
                .loginUser(new RegisterOrLoginUserRequest(email, password))
                .execute();
    }

    @Step("Update user")
    @SneakyThrows
    public Response<UpdateUserResponse> updateUser (
            int id, String email, String firstName, String lastName, String avatar) {
        return userService.updateUser(
                        id, new UpdateUserRequest(email, firstName, lastName, avatar))
                .execute();
    }

    @Step("Delete user")
    @SneakyThrows
    public Response<Void> deleteUser(int id) {
        return userService.deleteUser(id)
                .execute();
    }


    @Step("Generating valid user ID")
    public int generateValidId() {
        return random.generateValidId();
    }

    @Step("Generating password")
    public String generatePassword() {
        return random.generatePassword();
    }

    @Step("Generating email")
    public String generateEmail(String domain) { return random.generateEmail(domain); }

    @Step("Generating first name")
    public String generateFirstName() { return random.generateFirstName(); }

    @Step("Generate lastname")
    public String generateLastName() { return random.generateSurname(); }

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
    public void checkUserById(GetUserByIdResponse responseBody, int id) {
        Assertions.assertThat(responseBody.getData().getId())
                .withFailMessage("Not equal id")
                .isEqualTo(id);
    }

    @Step("Check user registration")
    public void checkRegistration(RegisterUserResponse registerBody, GetUserByIdResponse userBody,
                                  String email) {
        Assertions.assertThat(registerBody.getId())
                .withFailMessage("ID in register body not equal ID user body")
                .isEqualTo(userBody.getData().getId());
        Assertions.assertThat(email)
                .withFailMessage("Registration email not equal email user")
                .isEqualTo(userBody.getData().getEmail());
    }




}
