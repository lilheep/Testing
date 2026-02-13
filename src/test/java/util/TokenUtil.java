package util;

import models.response.appuser.RootLoginUserResponse;
import models.response.appuser.RootVerifyTokenResponse;
import retrofit2.Response;
import tests.steps.AppUserSteps;

import java.io.IOException;

public class TokenUtil {
    private static String sessionToken;

    public static void setToken(AppUserSteps appUserStep) throws IOException {
        if (sessionToken == null || sessionToken.isEmpty()) {
            Response<RootLoginUserResponse> responseLogin = appUserStep.loginUser();
            RootLoginUserResponse responseLoginBody = responseLogin.body();
            appUserStep.setValueTokenApp(responseLoginBody.getData());

            Response<RootVerifyTokenResponse> responseVerify = appUserStep.verifyToken();

            RootVerifyTokenResponse responseVerifyBody = responseVerify.body();
            sessionToken = responseVerifyBody.getData().getSessionToken();
        }
    }
    public static String getToken() {
        return sessionToken;
    }
}
