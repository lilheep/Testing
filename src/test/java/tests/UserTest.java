package tests;

import api.ResourceService;
import api.UserService;
import client.ApiClient;
import constants.ApiConstants;
import models.response.resource.GetResourceByIdResponse;
import models.response.resource.GetResourcesResponse;
import models.response.resource.ResourceResponse;
import models.response.user.GetUserByIdResponse;
import models.response.user.GetUsersResponse;
import models.response.user.UserResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;
import util.RandomUtil;

import java.io.IOException;
import java.util.List;

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
}
