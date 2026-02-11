package api;

import models.request.appuser.LoginUserRequest;
import models.request.appuser.VerifyTokenRequest;
import models.response.appuser.RootLoginUserResponse;
import models.response.appuser.RootVerifyTokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AppUserService {
    @POST("/api/app-users/login")
    Call<RootLoginUserResponse> loginUser(@Header("x-api-key") String token,
                                          @Body LoginUserRequest data);
    @POST("/api/app-users/verify")
    Call<RootVerifyTokenResponse> verifyToken(@Header("x-api-key") String token,
                                              @Body VerifyTokenRequest data);
}
