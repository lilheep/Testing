package tests;

import api.ResourceService;
import api.UserService;
import client.ApiClient;
import constants.ApiConstants;
import models.request.RegisterOrLoginUserRequest;
import models.request.UpdateUserRequest;
import models.response.resource.GetResourceByIdResponse;
import models.response.resource.GetResourcesResponse;
import models.response.resource.ResourceResponse;
import models.response.user.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;
import util.RandomUtil;

import java.io.IOException;

public class UserTest {
    private static final ApiClient apiclient = new ApiClient();
    private static final UserService userService = apiclient.getUserService();
    private static final ResourceService resourceService = apiclient.getResourceService();
    private final RandomUtil random = new RandomUtil();

    @Test
    public void test1() throws IOException {
        int page = 1;
        int perPage = 6;
        int total = 12;
        Response<GetUsersResponse> response = userService
                .getUserList(ApiConstants.getToken())
                .execute();

        Assertions.assertThat(response.isSuccessful());
        GetUsersResponse responseBody = response.body();
        Assertions.assertThat(responseBody).isNotNull();

        Assertions.assertThat(responseBody.getPage()).isEqualTo(page);
        Assertions.assertThat(responseBody.getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getData().size()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getTotal()).isEqualTo(total);
    }

    @Test
    public void test2() throws IOException {
        int page = 1;
        int perPage = 6;
        Response<GetUsersResponse> response = userService
                .getUserList(ApiConstants.getToken(), page)
                .execute();

        Assertions.assertThat(response.isSuccessful());

        GetUsersResponse responseBody = response.body();
        Assertions.assertThat(responseBody).isNotNull();

        Assertions.assertThat(responseBody.getPage()).isEqualTo(page);
        Assertions.assertThat(responseBody.getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getData().size()).isEqualTo(perPage);
    }

    @Test
    public void test3() throws IOException {
        int page = 2;
        int perPage = 3;

        Response<GetUsersResponse> response = userService.
                getUserList(ApiConstants.getToken(), page, perPage)
                .execute();

        Assertions.assertThat(response.isSuccessful());

        GetUsersResponse responseBody = response.body();
        Assertions.assertThat(responseBody).isNotNull();

        Assertions.assertThat(responseBody.getPage()).isEqualTo(page);
        Assertions.assertThat(responseBody.getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getData().size()).isEqualTo(perPage);
    }

    @Test
    public void test4() throws IOException {
        int id = random.generateValidId();
        Response<GetUserByIdResponse> response = userService
                .getUserById(ApiConstants.getToken(), id)
                .execute();

        Assertions.assertThat(response.isSuccessful());
        GetUserByIdResponse responseBody = response.body();

        Assertions.assertThat(responseBody).isNotNull();

        UserResponse userData = responseBody.getData();
        Assertions.assertThat(userData.getId()).isEqualTo(id);
    }


    @Test
    public void test5() throws IOException {
        int page = 1;
        int perPage = 6;
        int total = 12;
        Response<GetResourcesResponse> response = resourceService
                .getResourceList(ApiConstants.getToken())
                .execute();

        Assertions.assertThat(response.isSuccessful());

        GetResourcesResponse responseBody = response.body();
        Assertions.assertThat(responseBody).isNotNull();

        Assertions.assertThat(responseBody.getPage()).isEqualTo(page);
        Assertions.assertThat(responseBody.getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getData().size()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getTotal()).isEqualTo(total);
    }

    @Test
    public void test6() throws IOException {
        int page = 2;
        int perPage = 6;
        Response<GetResourcesResponse> response = resourceService
                .getResourceList(ApiConstants.getToken(), page)
                .execute();

        Assertions.assertThat(response.isSuccessful());

        GetResourcesResponse responseBody = response.body();
        Assertions.assertThat(responseBody).isNotNull();

        Assertions.assertThat(responseBody.getPage()).isEqualTo(page);
        Assertions.assertThat(responseBody.getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getData().size()).isEqualTo(perPage);
    }

    @Test
    public void test7() throws IOException {
        int page = 3;
        int perPage = 4;
        Response<GetResourcesResponse> response = resourceService
                .getResourceList(ApiConstants.getToken(), page, perPage)
                .execute();

        Assertions.assertThat(response.isSuccessful());

        GetResourcesResponse responseBody = response.body();
        Assertions.assertThat(responseBody).isNotNull();

        Assertions.assertThat(responseBody.getPage()).isEqualTo(page);
        Assertions.assertThat(responseBody.getPerPage()).isEqualTo(perPage);
        Assertions.assertThat(responseBody.getData().size()).isEqualTo(perPage);
    }

    @Test
    public void test8() throws IOException {
        int id = random.generateValidId();
        Response<GetResourceByIdResponse> response = resourceService
                .getResourceById(ApiConstants.getToken(), id)
                .execute();

        Assertions.assertThat(response.isSuccessful());

        GetResourceByIdResponse responseBody = response.body();
        Assertions.assertThat(responseBody).isNotNull();

        ResourceResponse resourceData = responseBody.getData();
        Assertions.assertThat(resourceData.getId()).isEqualTo(id);
    }

    @Test
    public void test9() throws IOException {
        int id = random.generateValidId();
        String email;
        String password = random.generatePassword();

        Response<GetUserByIdResponse> responseUser = userService
                .getUserById(ApiConstants.getToken(), id)
                .execute();

        Assertions.assertThat(responseUser.isSuccessful());

        GetUserByIdResponse responseUserBody = responseUser.body();
        Assertions.assertThat(responseUserBody).isNotNull();

        email = responseUserBody.getData().getEmail();

        Response<RegisterUserResponse> responseRegister = userService
                .registerUser(ApiConstants.getToken(), new RegisterOrLoginUserRequest(email, password))
                .execute();

        Assertions.assertThat(responseRegister.isSuccessful());

        RegisterUserResponse responseRegisterBody = responseRegister.body();
        Assertions.assertThat(responseRegisterBody).isNotNull();

        Assertions.assertThat(responseUserBody.getData().getId()).isEqualTo(responseRegisterBody.getId());
        Assertions.assertThat(responseUserBody.getData().getEmail()).isEqualTo(email);
    }

    @Test
    public void test10() throws IOException {
        int id = random.generateValidId();
        String email;
        String password = random.generatePassword();

        Response<GetUserByIdResponse> responseUser = userService
                .getUserById(ApiConstants.getToken(), id)
                .execute();

        Assertions.assertThat(responseUser.isSuccessful());

        GetUserByIdResponse responseUserBody = responseUser.body();
        Assertions.assertThat(responseUserBody).isNotNull();

        email = responseUserBody.getData().getEmail();

        Response<LoginUserResponse> responseLogin = userService
                .loginUser(ApiConstants.getToken(), new RegisterOrLoginUserRequest(email, password))
                .execute();

        Assertions.assertThat(responseLogin.isSuccessful());

        LoginUserResponse responseLoginBody = responseLogin.body();
        Assertions.assertThat(responseLoginBody).isNotNull();
    }

    @Test
    public void test11() throws IOException {
        int id = random.generateValidId();
        String email = random.generateEmail("gmail.com");
        String firstName = random.generateFirstName();
        String lastName = random.generateSurname();
        String avatar = "https://reqres.in/img/faces/13-image.jpg";

        Response<UpdateUserResponse> response = userService
                .updateUser(ApiConstants.getToken(), id, new UpdateUserRequest(
                        email, firstName, lastName, avatar)
                ).execute();

        Assertions.assertThat(response.isSuccessful());
    }

    @Test
    public void test12() throws IOException {
        int id = random.generateValidId();
        Response<Void> response = userService
                .deleteUser(ApiConstants.getToken(), id)
                .execute();

        Assertions.assertThat(response.code()).isEqualTo(204);
    }
}
