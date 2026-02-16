package api;

import models.request.user.RegisterOrLoginUserRequest;
import models.request.user.UpdateUserRequest;
import models.response.user.*;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.Query;
import retrofit2.http.Path;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;

public interface UserService {
    @GET("/api/users")
    Call<GetUsersResponse> getUserList();
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Query("page") int page);
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Query("page") int page,
                                       @Query("per_page") int perPage);
    @GET("/api/users/{id}")
    Call<GetUserByIdResponse> getUserById(@Path("id") int id);
    @POST("/api/register")
    Call<RegisterUserResponse> registerUser(@Body RegisterOrLoginUserRequest data);
    @POST("/api/login")
    Call<LoginUserResponse> loginUser(@Body RegisterOrLoginUserRequest data);
    @PUT("/api/users/{id}")
    Call<UpdateUserResponse> updateUser(@Path("id") int id,
                                       @Body UpdateUserRequest data);
    @DELETE("/api/users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
    @POST("/api/logout")
    Call<Void> logoutUser();
}
