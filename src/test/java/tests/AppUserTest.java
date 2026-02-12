package tests;

import models.response.appuser.*;
import org.testng.annotations.Test;
import retrofit2.Response;
import java.io.IOException;

public class AppUserTest extends BaseTest {

    private String getSessionToken() {
        return appUserStep.getValueSessionToken();
    }

    private String getTokenApp() {
        return appUserStep.getValueTokenApp();
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
        Response<RootAddUserProjectResponse> response = appUserStep.addUserToProject(email);
        appUserStep.checkResponseIsSuccessful(response);
        RootAddUserProjectResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
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
}
