package tests;

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

}
