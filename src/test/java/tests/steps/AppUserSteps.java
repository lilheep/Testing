package tests.steps;

import api.AppUserService;
import client.ApiClient;
import constants.ApiConstants;
import io.qameta.allure.Step;
import models.request.appuser.LoginUserRequest;
import models.request.appuser.VerifyTokenRequest;
import models.response.appuser.*;
import org.assertj.core.api.Assertions;
import retrofit2.Response;

import java.io.IOException;

public class AppUserSteps {
    private final ApiClient apiclient = new ApiClient(getToken());
    private final AppUserService appUserService = apiclient.getAppUserService();

    private String getToken() { return ApiConstants.getToken(); }

    public ApiClient getApiClient() { return apiclient; }

    @Step("Login user")
    public Response<RootLoginUserResponse> loginUser() throws IOException {
        return appUserService
                .loginUser(new LoginUserRequest(
                        getUserEmail(), getProjectId())).execute();
    }

    @Step("Verify token")
    public Response<RootVerifyTokenResponse> verifyToken() throws IOException {
        return appUserService.verifyToken(new VerifyTokenRequest(ApiConstants.getTokenApp()))
                .execute();
    }

    @Step("Get current user info")
    public Response<RootUserMeResponse> getMeUser() throws IOException {
        return appUserService.getMeUser().execute();
    }

    @Step("Get user email")
    public String getUserEmail() {
        return ApiConstants.getEmail();
    }

    @Step("Get project id")
    public String getProjectId() {
        return ApiConstants.getProjectId();
    }

    @Step("Get value token app")
    public String getValueTokenApp() {
        Assertions.assertThat(ApiConstants.getTokenApp())
                .withFailMessage("Token app is null")
                .isNotNull();
        return ApiConstants.getTokenApp();
    }

    @Step("Set value token app")
    public void setValueTokenApp(LoginUserResponse loginUserResponse) {
        ApiConstants.setTokenApp(loginUserResponse.getToken());
    }

    @Step("Set session token")
    public void setValueSessionToken(VerifyTokenResponse verifyTokenResponse) {
        ApiConstants.setSessionToken(verifyTokenResponse.getSessionToken());
    }

    @Step("Get session token")
    public String getValueSessionToken() {
        Assertions.assertThat(ApiConstants.getSessionToken())
                .withFailMessage("Session token is null")
                .isNotNull();

        return ApiConstants.getSessionToken();
    }

    @Step("Checking success response")
    public void checkResponseIsSuccessful(Response<?> response) {
        Assertions.assertThat(response.isSuccessful())
                .withFailMessage("Incorrect status code: " + response.code())
                .isTrue();
    }

    @Step("Check response body not null")
    public <T> T checkResponseBodyNotNull(Response<T> response) {
        T responseBody = response.body();
        Assertions.assertThat(responseBody)
                .withFailMessage("Response body is null")
                .isNotNull();
        return responseBody;
    }

    @Step("Check login user")
    public void checkLoginUser(RootLoginUserResponse response) {
        Assertions.assertThat(response.getData().getToken())
                .withFailMessage("Token is null")
                .isNotNull();
    }

    @Step("Check verify token")
    public void checkVerifyToken(RootVerifyTokenResponse response) {
        Assertions.assertThat(response.getData().getSessionToken())
                .withFailMessage("Token is null")
                .isNotNull();
    }
}
