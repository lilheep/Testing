package api;

import models.request.appuser.AddUserProjectRequest;
import models.request.appuser.LoginUserRequest;
import models.request.appuser.UpdateUserRequest;
import models.request.appuser.VerifyTokenRequest;
import models.response.appuser.*;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.Query;
import retrofit2.http.Path;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;

public interface AppUserService {
    @POST("/api/app-users/login")
    Call<RootLoginUserResponse> loginUser(@Body LoginUserRequest data);

    @POST("/api/app-users/verify")
    Call<RootVerifyTokenResponse> verifyToken(@Body VerifyTokenRequest data);

    @GET("api/app-users/me")
    Call<RootUserMeResponse> getMeUser();

    @GET("api/app-users")
    Call<RootGetListUsersResponse> getListUsers();

    @GET("api/app-users")
    Call<RootGetListUsersResponse> getListUsers(@Query("limit") int limit);

    @POST("api/app-users")
    Call<RootAddOrUpdateUserProjectResponse> addUserToProject(@Body AddUserProjectRequest data);

    @GET("api/app-users/{id}")
    Call<RootGetUserByIdResponse> getUserById(@Path("id") String id);

    @PUT("api/app-users/{id}")
    Call<RootAddOrUpdateUserProjectResponse> updateUser(@Path("id") String id,
                                                        @Body UpdateUserRequest data);

    @DELETE("api/app-users/{id}")
    Call<Void> deleteUser(@Path("id") String id);

    @GET("api/projects/{projectId}/app-users")
    Call<RootGetListUsersOnProjectResponse> getListUsersOnProject(@Path("projectId") String projectId);

    @GET("api/projects/{projectId}/app-users")
    Call<RootGetListUsersOnProjectResponse> getListUsersOnProject(@Path("projectId") String projectId,
                                                                  @Query("statuses") String statuses);

    @GET("api/projects/{projectId}/app-users/total")
    Call<GetTotalUsersOnProjectResponse> getTotalUsersOnProject(@Path("projectId") String projectId);
}
