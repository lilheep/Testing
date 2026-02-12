package tests;

import models.response.appuser.AddUserProjectResponse;
import models.response.appuser.RootGetListUsersResponse;
import org.testng.annotations.Test;
import retrofit2.Response;
import models.response.appuser.RootUserMeResponse;

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
        Response<AddUserProjectResponse> response = appUserStep.addUserToProject(email);
        appUserStep.checkResponseIsSuccessful(response);
        AddUserProjectResponse responseBody = appUserStep.checkResponseBodyNotNull(response);
        appUserStep.checkAddUserProject(responseBody, email);
    }
}
