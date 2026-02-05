package api;

import models.response.resource.GetResourceByIdResponse;
import models.response.resource.GetResourcesResponse;
import models.response.resource.ResourceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ResourceService {
    @GET("/api/unknown")
    Call<GetResourcesResponse> getResourceList(@Header("x-api-key") String token);
    @GET("/api/unknown")
    Call<GetResourcesResponse> getResourceList(@Header("x-api-key") String token,
                                           @Query("page") int page);
    @GET("/api/unknown")
    Call<GetResourcesResponse> getResourceList(@Header("x-api-key") String token,
                                           @Query("page") int page,
                                           @Query("per_page") int per_page);
    @GET("/api/unknown/{id}")
    Call<GetResourceByIdResponse> getResourceById(@Header("x-api-key") String token,
                                                  @Path("id") int id);

}
