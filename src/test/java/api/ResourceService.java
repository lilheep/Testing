package api;

import models.response.resource.GetResourceByIdResponse;
import models.response.resource.GetResourcesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ResourceService {
    @GET("/api/unknown")
    Call<GetResourcesResponse> getResourceList();

    @GET("/api/unknown")
    Call<GetResourcesResponse> getResourceList(@Query("page") int page);

    @GET("/api/unknown")
    Call<GetResourcesResponse> getResourceList(@Query("page") int page,
                                               @Query("per_page") int per_page);

    @GET("/api/unknown/{id}")
    Call<GetResourceByIdResponse> getResourceById(@Path("id") int id);

}
