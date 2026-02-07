package tests;

import models.response.user.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;
import tests.steps.UserSteps;

import java.io.IOException;

public class UserTest {
    private static final UserSteps userStep = new UserSteps();
    @Test
    public void getUsersTest() throws IOException {
        int page = 1;
        int perPage = 6;
        int total = 12;
        Response<GetUsersResponse> response = userStep.getUserList();

        userStep.checkResponseIsSuccessful(response);
        GetUsersResponse responseBody = userStep.checkResponseBodyNotNull(response);
        userStep.checkListUsers(responseBody, page, perPage, total);
    }

    @Test
    public void getUsersOnPageTest() throws IOException {
        int page = 1;
        int perPage = 6;
        int total = 12;
        Response<GetUsersResponse> response = userStep.getUserList(page);

        userStep.checkResponseIsSuccessful(response);
        GetUsersResponse responseBody = userStep.checkResponseBodyNotNull(response);
        userStep.checkListUsers(responseBody, page, perPage, total);
    }

    @Test
    public void getUsersOnPageAndPerTest() throws IOException {
        int page = 2;
        int perPage = 3;
        int total = 12;

        Response<GetUsersResponse> response = userStep.getUserList(page, perPage);
        userStep.checkResponseIsSuccessful(response);
        GetUsersResponse responseBody = userStep.checkResponseBodyNotNull(response);
        userStep.checkListUsers(responseBody, page, perPage, total);
    }

    @Test
    public void getUserByIdTest() throws IOException {
        int id = userStep.generateValidId();
        Response<GetUserByIdResponse> response = userStep.getUserById(id);

        userStep.checkResponseIsSuccessful(response);
        GetUserByIdResponse responseBody = userStep.checkResponseBodyNotNull(response);
        userStep.checkUserById(responseBody, id);
    }

    @Test
    public void registerUserTest() throws IOException {
        int id = userStep.generateValidId();
        String email;
        String password = userStep.generatePassword();

        Response<GetUserByIdResponse> responseUser = userStep.getUserById(id);
        userStep.checkResponseIsSuccessful(responseUser);

        GetUserByIdResponse responseUserBody = userStep.checkResponseBodyNotNull(responseUser);
        email = responseUserBody.getData().getEmail();

        Response<RegisterUserResponse> responseRegister = userStep.registerUser(email, password);
        userStep.checkResponseIsSuccessful(responseRegister);

        RegisterUserResponse responseRegisterBody = responseRegister.body();
        Assertions.assertThat(responseRegisterBody).isNotNull();

        userStep.checkRegistration(responseRegisterBody, responseUserBody, email);
    }

    @Test
    public void loginUserTest() throws IOException {
        int id = userStep.generateValidId();
        String email;
        String password = userStep.generatePassword();

        Response<GetUserByIdResponse> responseUser = userStep.getUserById(id);
        userStep.checkResponseIsSuccessful(responseUser);

        GetUserByIdResponse responseUserBody = userStep.checkResponseBodyNotNull(responseUser);
        email = responseUserBody.getData().getEmail();

        Response<LoginUserResponse> responseLogin = userStep.loginUser(email, password);
        userStep.checkResponseIsSuccessful(responseLogin);

        LoginUserResponse responseLoginBody = userStep.checkResponseBodyNotNull(responseLogin);
    }

    @Test
    public void updateUserTest() throws IOException {
        int id = userStep.generateValidId();
        String email = userStep.generateEmail("gmail.com");
        String firstName = userStep.generateFirstName();
        String lastName = userStep.generateLastName();
        String avatar = "https://reqres.in/img/faces/13-image.jpg";

        Response<UpdateUserResponse> response = userStep.updateUser(
                id, email, firstName, lastName, avatar
        );

        userStep.checkResponseIsSuccessful(response);
    }
    @Test
    public void deleteUserTest() throws IOException {
        int id = userStep.generateValidId();
        Response<Void> response = userStep.deleteUser(id);

        userStep.checkResponseIsSuccessful(response);

    }
}
