package tests;

import models.response.appuser.RootLoginUserResponse;
import models.response.appuser.RootVerifyTokenResponse;
import org.testng.annotations.BeforeSuite;
import retrofit2.Response;
import tests.steps.AppUserSteps;

import java.io.IOException;

public class BaseTest {
    protected static AppUserSteps appUserStep;

    @BeforeSuite
    public void setUp() throws IOException {
        appUserStep = new AppUserSteps();
        Response<RootLoginUserResponse> responseLogin = appUserStep.loginUser();
        appUserStep.checkResponseIsSuccessful(responseLogin);

        RootLoginUserResponse responseLoginBody = appUserStep.checkResponseBodyNotNull(responseLogin);
        appUserStep.checkLoginUser(responseLoginBody);
        appUserStep.setValueTokenApp(responseLoginBody.getData());

        Response<RootVerifyTokenResponse> responseVerify = appUserStep.verifyToken();
        appUserStep.checkResponseIsSuccessful(responseVerify);

        RootVerifyTokenResponse responseVerifyBody = appUserStep.checkResponseBodyNotNull(responseVerify);
        appUserStep.checkVerifyToken(responseVerifyBody);
        appUserStep.setValueSessionToken(responseVerifyBody.getData());

        appUserStep.getApiKeyClient().setBearerToken(appUserStep.getValueSessionToken());
        appUserStep.getApiEnvClient().setBearerToken(appUserStep.getValueSessionToken());
    }
}
