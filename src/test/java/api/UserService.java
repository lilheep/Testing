package api;

import models.request.RegisterOrLoginUserRequest;
import models.request.UpdateUserRequest;
import models.response.user.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Header("x-api-key") String token);
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Header("x-api-key") String token,
                                       @Query("page") int page);
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Header("x-api-key") String token,
                                       @Query("page") int page,
                                       @Query("per_page") int perPage);
    @GET("/api/users/{id}")
    Call<GetUserByIdResponse> getUserById(@Header("x-api-key") String token,
                                   @Path("id") int id);
    @POST("/api/register")
    Call<RegisterUserResponse> registerUser(@Header("x-api-key") String token,
                                            @Body RegisterOrLoginUserRequest data);
    @POST("/api/login")
    Call<LoginUserResponse> loginUser(@Header("x-api-key") String token,
                                      @Body RegisterOrLoginUserRequest data);
    @PUT("/api/users/{id}")
    Call<UpdateUserResponse> updateUser(@Header("x-api-key") String token,
                                       @Path("id") int id,
                                       @Body UpdateUserRequest data);
    @DELETE("/api/users/{id}")
    Call<Void> deleteUser(@Header("x-api-key") String token,
                    @Path("id") int id);
}
