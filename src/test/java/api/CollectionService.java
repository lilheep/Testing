package api;

import models.request.collection.CreateCollectionRequest;
import models.request.collection.RootCreateRecordRequest;
import models.request.collection.RootUpdateRecordRequest;
import models.request.collection.UpdateCollectionRequest;
import models.response.collection.RootCreateRecordResponse;
import models.response.collection.RootGetCollectionBySlugResponse;
import models.response.collection.RootGetCollectionsResponse;
import models.response.collection.RootGetListRecordsResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface CollectionService {
    @POST("/api/collections")
    Call<RootGetCollectionBySlugResponse> createCollection(@Body CreateCollectionRequest data);
    @DELETE("api/collections/{slug}")
    Call<Void> deleteCollection(@Path("slug") String slug);
    @PUT("/api/collections/{slug}")
    Call<RootGetCollectionBySlugResponse> updateCollection(@Path("slug") String slug,
                                                           @Body UpdateCollectionRequest data);
    @GET("/app/collections")
    Call<RootGetCollectionsResponse> getListCollections();
    @GET("/app/collections/{slug}")
    Call<RootGetCollectionBySlugResponse> getCollectionBySlug(@Path("slug") String slug);
    @POST("/api/collections/{slug}/records")
    Call<RootCreateRecordResponse> createRecord(@Path("slug") String slug,
                                                @Body RootCreateRecordRequest data);
    @GET("/api/collections/{slug}/records")
    Call<RootGetListRecordsResponse> getListRecords(@Path("slug") String slug);
    @GET("/api/collections/{slug}/records")
    Call<RootGetListRecordsResponse> getListRecords(@Path("slug") String slug,
                                                    @Query("limit") int limit);
    @GET("/api/collections/{slug}/records/{recordId}")
    Call<RootCreateRecordResponse> getRecordById(@Path("slug") String slug,
                                                 @Path("recordId") String recordId);
    @PUT("/api/collections/{slug}/records/{recordId}")
    Call<RootCreateRecordResponse> updateRecord(@Path("slug") String slug,
                                                @Path("recordId") String recordId,
                                                @Body RootUpdateRecordRequest data);
    @DELETE("/api/collections/{slug}/records/{recordId}")
    Call<Void> deleteRecord(@Path("slug") String slug,
                            @Path("recordId") String recordId);
}
