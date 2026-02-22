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
            appUserStep.checkResponseIsSuccessful(responseLogin);
            RootLoginUserResponse responseLoginBody = appUserStep.checkResponseBodyNotNull(responseLogin);
            appUserStep.checkLoginUser(responseLoginBody);
            appUserStep.setValueTokenApp(responseLoginBody.getData());

            Response<RootVerifyTokenResponse> responseVerify = appUserStep.verifyToken();
            appUserStep.checkResponseIsSuccessful(responseVerify);
            RootVerifyTokenResponse responseVerifyBody = appUserStep.checkResponseBodyNotNull(responseVerify);
            appUserStep.checkVerifyToken(responseVerifyBody);
            sessionToken = responseVerifyBody.getData().getSessionToken();
        }
    }

    public static String getToken() {
        return sessionToken;
    }
}
