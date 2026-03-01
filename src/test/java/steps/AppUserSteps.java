package steps;

import api.AppUserService;
import client.ApiKeyClient;
import config.ConfigProvider;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.SneakyThrows;
import models.request.appuser.AddUserProjectRequest;
import models.request.appuser.LoginUserRequest;
import models.request.appuser.UpdateUserRequest;
import models.request.appuser.VerifyTokenRequest;
import models.response.appuser.*;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import tests.BaseTest;
import util.RandomUtil;

public class AppUserSteps {
    private final BaseTest baseTest;
    @Getter
    private final ApiKeyClient apiKeyClient;
    private final AppUserService appUserServiceForApiKey;
    private final RandomUtil random = new RandomUtil();

    public AppUserSteps(BaseTest baseTest) {
        this.baseTest = baseTest;
        this.apiKeyClient = new ApiKeyClient(getToken());
        this.appUserServiceForApiKey = apiKeyClient.setService(AppUserService.class);
    }

    private String getToken() { return ConfigProvider.getToken(); }

    @Step("Login user")
    @SneakyThrows
    public Response<RootLoginUserResponse> loginUser() {
        return appUserServiceForApiKey
                .loginUser(new LoginUserRequest(
                        getUserEmail(), getProjectId())).execute();
    }

    @Step("Verify token")
    @SneakyThrows
    public Response<RootVerifyTokenResponse> verifyToken() {
        return appUserServiceForApiKey.verifyToken(new VerifyTokenRequest(baseTest.getTokenApp()))
                .execute();
    }

    @Step("Get current user info")
    @SneakyThrows
    public Response<RootUserMeResponse> getMeUser() {
        return appUserServiceForApiKey.getMeUser().execute();
    }

    @Step("Get list users")
    @SneakyThrows
    public Response<RootGetListUsersResponse> getListUsers() {
        return appUserServiceForApiKey.getListUsers().execute();
    }

    @Step("Get list users on limit")
    @SneakyThrows
    public Response<RootGetListUsersResponse> getListUsers(int limit) {
        return appUserServiceForApiKey.getListUsers(limit).execute();
    }

    @Step("Add user to project")
    @SneakyThrows
    public Response<RootAddOrUpdateUserProjectResponse> addUserToProject(String email) {
        return appUserServiceForApiKey.addUserToProject(new AddUserProjectRequest(email)).execute();
    }

    @Step("Get user by ID")
    @SneakyThrows
    public Response<RootGetUserByIdResponse> getUserById(String id) {
        return appUserServiceForApiKey.getUserById(id).execute();
    }

    @Step("Update user by ID")
    @SneakyThrows
    public Response<RootAddOrUpdateUserProjectResponse> updateUser(String id, String email, String status) {
        return appUserServiceForApiKey.updateUser(id, new UpdateUserRequest(email, status)).execute();
    }

    @Step("Delete user by ID")
    @SneakyThrows
    public Response<Void> deleteUser(String id) {
        return appUserServiceForApiKey.deleteUser(id).execute();
    }

    @Step("Get list users on project")
    @SneakyThrows
    public Response<RootGetListUsersOnProjectResponse> getListUsersOnProject(String projectId) {
        return appUserServiceForApiKey.getListUsersOnProject(projectId).execute();
    }

    @Step("Get list users on project with filtration by status")
    @SneakyThrows
    public Response<RootGetListUsersOnProjectResponse> getListUsersOnProject(String projectId, String status) {
        return appUserServiceForApiKey.getListUsersOnProject(projectId, status).execute();
    }

    @Step("Get count user on project")
    @SneakyThrows
    public Response<GetTotalUsersOnProjectResponse> getTotalUsersOnProjectResponse(String projectId) {
        return appUserServiceForApiKey.getTotalUsersOnProject(projectId).execute();
    }
    @Step("Generating email")
    public String generateEmail(String domain) { return random.generateEmail(domain); }

    @Step("Get user email")
    public String getUserEmail() {
        return ConfigProvider.getEmail();
    }

    @Step("Get project id")
    public String getProjectId() {
        return ConfigProvider.getProjectId();
    }

    @Step("Get my user id")
    public String getMyUserId() { return ConfigProvider.getMyId(); }

    @Step("Set value token app")
    public void setValueTokenApp(LoginUserResponse loginUserResponse) {
        baseTest.setTokenApp(loginUserResponse.getToken());
    }

    @Step("Generate valid user ID")
    public String generateValidUserId(RootGetListUsersResponse responseBody) {
        return random.generateUserIdOnProject(responseBody);
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

    @Step("Check list users")
    public void checkListUsers(RootGetListUsersResponse response, int limit) {
        Assertions.assertThat(response.getData().size())
                .withFailMessage("Count users not equal limit")
                .isEqualTo(limit);
    }

    @Step("Check add user to project")
    public void checkAddUserProject(RootAddOrUpdateUserProjectResponse response, String email) {
        Assertions.assertThat(response.getData().getEmail().toLowerCase())
                .withFailMessage("Email in request not equal email in response")
                .isEqualTo(email.toLowerCase());
    }

    @Step("Check user exists on list users")
    public void checkUserExistsInList(RootGetListUsersResponse response, String email) {
        boolean existsUser = false;
        for (var user : response.getData()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                existsUser = true;
                break;
            }
        }

        Assertions.assertThat(existsUser)
                .withFailMessage("User not found in list users")
                .isTrue();
    }

    @Step("Check get user by ID")
    public void checkGetUserById(RootGetUserByIdResponse response, String id) {
        Assertions.assertThat(response.getData().getId())
                .withFailMessage("User id not equal path id")
                .isEqualTo(id);
    }

    @Step("Check update user")
    public void checkUpdateUser(RootAddOrUpdateUserProjectResponse response, String id,
                                String email, String status) {
        Assertions.assertThat(response.getData().getId())
                .withFailMessage("User id not equal request id")
                .isEqualTo(id);
        Assertions.assertThat(response.getData().getEmail().toLowerCase())
                .withFailMessage("New email not equals request email")
                .isEqualTo(email.toLowerCase());
        Assertions.assertThat(response.getData().getStatus())
                .withFailMessage("New status not equals request status")
                .isEqualTo(status);
    }

    @Step("Checking status in filtration by status list users on project")
    public void checkListUsersOnProject(RootGetListUsersOnProjectResponse responseBody, String status) {
        boolean filterUser = true;
        for (var user : responseBody.getData()) {
            if (!user.getStatus().equals(status)) {
                filterUser = false;
                break;
            }
        }

        Assertions.assertThat(filterUser)
                .withFailMessage("Status not equal status in filter")
                .isTrue();
    }

    @Step("Generate random status user on project")
    public String generateStatusUser() {
        return random.generateStatusUserOnProject();
    }

    @Step("Check total users on project")
    public void checkTotalUserOnProject(GetTotalUsersOnProjectResponse responseTotal,
                                        RootGetListUsersOnProjectResponse responseUsers) {
        Assertions.assertThat(responseTotal.getTotal())
                .withFailMessage("Total not equal count users in list")
                .isEqualTo(responseUsers.getData().size());

    }
}
