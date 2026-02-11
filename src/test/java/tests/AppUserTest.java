package tests;

import models.response.appuser.LoginUserResponse;
import models.response.appuser.RootLoginUserResponse;
import models.response.appuser.RootVerifyTokenResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;
import tests.steps.AppUserSteps;

import java.io.IOException;

public class AppUserTest extends BaseTest {

    private String getSessionToken() {
        return appUserStep.getValueSessionToken();
    }

    private String getTokenApp() {
        return appUserStep.getValueTokenApp();
    }

    @Test
    public void checkingTokens() {

    }
}
