package tests;

import constants.ApiConstants;
import models.response.appuser.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;
import tests.steps.AppUserSteps;
import util.TokenUtil;

import java.io.IOException;

public class AppUserTest extends BaseTest {
    private final AppUserSteps appUserStep = new AppUserSteps();

    @BeforeClass
    public void setUp() throws IOException {
        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();

        appUserStep.getApiKeyClient().setBearerToken(token);
    }

    @Test
    public void getInfoCurrentUserTest() throws IOException {
        Response<RootUserMeResponse> response = appUserStep.getMeUser();
        appUserStep.checkResponseIsSuccessful(response);
    }

    @Test
    public void getListUsersTest() throws IOException {
        int limit = 1;
        Response<RootGetListUsersResponse> response = appUserStep.getListUsers(limit);
        appUserStep.checkResponseIsSuccessful(response);
        RootGetListUsersResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkListUsers(responseBody, limit);
    }

    @Test
    public void addUserToProjectTest() throws IOException {
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
    public void getUserByIdTest() throws IOException {
        String id = appUserStep.getMyUserId();
        Response<RootGetUserByIdResponse> response = appUserStep.getUserById(id);
        appUserStep.checkResponseIsSuccessful(response);
        RootGetUserByIdResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkGetUserById(responseBody, id);
    }

    @Test
    public void updateUserTest() throws IOException {
        String id = appUserStep.getMyUserId();
        String email = appUserStep.getUserEmail();
        String status = "active";
        Response<RootAddOrUpdateUserProjectResponse> response = appUserStep.updateUser(id, email, status);
        appUserStep.checkResponseIsSuccessful(response);
        RootAddOrUpdateUserProjectResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkUpdateUser(responseBody, id, email, status);
    }

    @Test
    public void deleteUserTest() throws IOException {
        Response<RootGetListUsersResponse> response = appUserStep.getListUsers();
        appUserStep.checkResponseIsSuccessful(response);
        RootGetListUsersResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        String id = appUserStep.generateValidUserId(responseBody);
        Response<Void> responseDelete = appUserStep.deleteUser(id);
        appUserStep.checkResponseIsSuccessful(responseDelete);
    }

    @Test
    public void getListUsersOnProjectTest() throws IOException {
        Response<RootGetListUsersOnProjectResponse> response = appUserStep.getListUsersOnProject(ApiConstants.getProjectId());
        appUserStep.checkResponseIsSuccessful(response);
    }

    @Test
    public void getListAllUsersOnProjectTest() throws IOException {
        String status = "all";
        Response<RootGetListUsersOnProjectResponse> response = appUserStep.getListUsersOnProject(
                ApiConstants.getProjectId(), status
        );
        appUserStep.checkResponseIsSuccessful(response);
    }

    @Test
    public void getListUsersOnProjectWithFilterTest() throws IOException {
        String status = appUserStep.generateStatusUser();
        Response<RootGetListUsersOnProjectResponse> response = appUserStep.
                getListUsersOnProject(ApiConstants.getProjectId(), status);
        appUserStep.checkResponseIsSuccessful(response);
        RootGetListUsersOnProjectResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkListUsersOnProject(responseBody, status);
    }

    @Test
    public void getTotalUsersOnProjectTest() throws IOException {
        Response<GetTotalUsersOnProjectResponse> responseTotal = appUserStep
                .getTotalUsersOnProjectResponse(ApiConstants.getProjectId());
        appUserStep.checkResponseIsSuccessful(responseTotal);
        GetTotalUsersOnProjectResponse responseTotalBody = appUserStep.checkResponseBodyNotNull(responseTotal);

        String status = "active";
        Response<RootGetListUsersOnProjectResponse> responseUsers = appUserStep
                .getListUsersOnProject(ApiConstants.getProjectId(), status);
        appUserStep.checkResponseIsSuccessful(responseUsers);
        RootGetListUsersOnProjectResponse responseBodyUsers = appUserStep.checkResponseBodyNotNull(responseUsers);

        appUserStep.checkTotalUserOnProject(responseTotalBody, responseBodyUsers);
    }




}
