package api;

import models.request.appuser.AddUserProjectRequest;
import models.request.appuser.LoginUserRequest;
import models.request.appuser.VerifyTokenRequest;
import models.response.appuser.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    Call<AddUserProjectResponse> addUserToProject(@Body AddUserProjectRequest data);
}
