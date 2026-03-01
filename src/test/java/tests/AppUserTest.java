package tests;

import config.ConfigProvider;
import lombok.SneakyThrows;
import models.response.appuser.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;
import util.TokenUtil;


public class AppUserTest extends BaseTest {

    @BeforeClass
    @SneakyThrows
    public void setUp() {
        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();

        appUserStep.getApiKeyClient().setBearerToken(token);
    }

    @Test
    @SneakyThrows
    public void getInfoCurrentUserTest() {
        Response<RootUserMeResponse> response = appUserStep.getMeUser();
        appUserStep.checkResponseIsSuccessful(response);
    }

    @Test
    @SneakyThrows
    public void getListUsersTest() {
        int limit = 1;
        Response<RootGetListUsersResponse> response = appUserStep.getListUsers(limit);
        appUserStep.checkResponseIsSuccessful(response);
        RootGetListUsersResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkListUsers(responseBody, limit);
    }

    @Test
    @SneakyThrows
    public void addUserToProjectTest() {
        String email = appUserStep.generateEmail("gmail.com");
        Response<RootAddOrUpdateUserProjectResponse> response = appUserStep.addUserToProject(email);
        appUserStep.checkResponseIsSuccessful(response);
        RootAddOrUpdateUserProjectResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkAddUserProject(responseBody, email);

        Response<RootGetListUsersResponse> responseUsers = appUserStep.getListUsers();
        appUserStep.checkResponseIsSuccessful(responseUsers);
        RootGetListUsersResponse responseUsersBody = appUserStep.checkResponseBodyNotNull(responseUsers);
        appUserStep.checkUserExistsInList(responseUsersBody, email);
    }

    @Test
    @SneakyThrows
    public void getUserByIdTest() {
        String id = appUserStep.getMyUserId();
        Response<RootGetUserByIdResponse> response = appUserStep.getUserById(id);
        appUserStep.checkResponseIsSuccessful(response);
        RootGetUserByIdResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkGetUserById(responseBody, id);
    }

    @Test
    @SneakyThrows
    public void updateUserTest() {
        String id = appUserStep.getMyUserId();
        String email = appUserStep.getUserEmail();
        String status = "active";
        Response<RootAddOrUpdateUserProjectResponse> response = appUserStep.updateUser(id, email, status);
        appUserStep.checkResponseIsSuccessful(response);
        RootAddOrUpdateUserProjectResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkUpdateUser(responseBody, id, email, status);
    }

    @Test
    @SneakyThrows
    public void deleteUserTest() {
        Response<RootGetListUsersResponse> response = appUserStep.getListUsers();
        appUserStep.checkResponseIsSuccessful(response);
        RootGetListUsersResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        String id = appUserStep.generateValidUserId(responseBody);
        Response<Void> responseDelete = appUserStep.deleteUser(id);
        appUserStep.checkResponseIsSuccessful(responseDelete);
    }

    @Test
    @SneakyThrows
    public void getListUsersOnProjectTest() {
        Response<RootGetListUsersOnProjectResponse> response = appUserStep.getListUsersOnProject(ConfigProvider.getProjectId());
        appUserStep.checkResponseIsSuccessful(response);
    }

    @Test
    @SneakyThrows
    public void getListAllUsersOnProjectTest() {
        String status = "all";
        Response<RootGetListUsersOnProjectResponse> response = appUserStep.getListUsersOnProject(
                ConfigProvider.getProjectId(), status
        );
        appUserStep.checkResponseIsSuccessful(response);
    }

    @Test
    @SneakyThrows
    public void getListUsersOnProjectWithFilterTest() {
        String status = appUserStep.generateStatusUser();
        Response<RootGetListUsersOnProjectResponse> response = appUserStep.
                getListUsersOnProject(ConfigProvider.getProjectId(), status);
        appUserStep.checkResponseIsSuccessful(response);
        RootGetListUsersOnProjectResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkListUsersOnProject(responseBody, status);
    }

    @Test
    @SneakyThrows
    public void getTotalUsersOnProjectTest() {
        Response<GetTotalUsersOnProjectResponse> responseTotal = appUserStep
                .getTotalUsersOnProjectResponse(ConfigProvider.getProjectId());
        appUserStep.checkResponseIsSuccessful(responseTotal);
        GetTotalUsersOnProjectResponse responseTotalBody = appUserStep.checkResponseBodyNotNull(responseTotal);

        String status = "active";
        Response<RootGetListUsersOnProjectResponse> responseUsers = appUserStep
                .getListUsersOnProject(ConfigProvider.getProjectId(), status);
        appUserStep.checkResponseIsSuccessful(responseUsers);
        RootGetListUsersOnProjectResponse responseBodyUsers = appUserStep.checkResponseBodyNotNull(responseUsers);

        appUserStep.checkTotalUserOnProject(responseTotalBody, responseBodyUsers);
    }




}
