package tests.steps;

import api.UserService;
import client.ApiKeyClient;
import constants.ApiConstants;
import io.qameta.allure.Step;
import models.request.user.RegisterOrLoginUserRequest;
import models.request.user.UpdateUserRequest;
import models.response.user.*;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import util.RandomUtil;

import java.io.IOException;

public class UserSteps {
    private final ApiKeyClient apiKeyClient = new ApiKeyClient(getToken());
    private final UserService userService = apiKeyClient.setService(UserService.class);
    private final RandomUtil random = new RandomUtil();

    private String getToken() {
        return ApiConstants.getToken();
    }

    @Step("Getting list users")
    public Response<GetUsersResponse> getUserList() throws IOException {
        return userService.getUserList().execute();
    }

    @Step("Getting list users on page")
    public Response<GetUsersResponse> getUserList(int page) throws  IOException {
        return userService.getUserList(page).execute();
    }

    @Step("Getting list users on page and per page")
    public Response<GetUsersResponse> getUserList(int page, int perPage) throws IOException {
        return userService.getUserList(page, perPage).execute();
    }

    @Step("Getting user by id")
    public Response<GetUserByIdResponse> getUserById(int id) throws IOException {
        return userService.getUserById(id).execute();
    }

    @Step("Registration user")
    public Response<RegisterUserResponse> registerUser(String email, String password) throws IOException {
        return userService
                .registerUser(new RegisterOrLoginUserRequest(email, password))
                .execute();
    }

    @Step("Logout user")
    public Response<Void> logoutUser() throws IOException {
        return userService.logoutUser().execute();
    }

    @Step("Login user")
    public Response<LoginUserResponse> loginUser(String email, String password) throws IOException {
        return userService
                .loginUser(new RegisterOrLoginUserRequest(email, password))
                .execute();
    }

    @Step("Update user")
    public Response<UpdateUserResponse> updateUser (
            int id, String email, String firstName, String lastName, String avatar) throws IOException {
        return userService.updateUser(
                        id, new UpdateUserRequest(email, firstName, lastName, avatar))
                .execute();
    }

    @Step("Delete user")
    public Response<Void> deleteUser(int id) throws IOException {
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
