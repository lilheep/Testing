package api;

import models.response.GetUserByIdResponse;
import models.response.GetUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Header("x-api-key") String token,
                                       @Query("page") int page);
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Header("x-api-key") String token);
    @GET("/api/users")
    Call<GetUsersResponse> getUserList(@Header("x-api-key") String token,
                                       @Query("page") int page,
                                       @Query("per_page") int perPage);

    @GET("/api/users/{id}")
    Call<GetUserByIdResponse> getUserById(@Header("x-api-key") String token,
                                          @Path("id") int id);
}
