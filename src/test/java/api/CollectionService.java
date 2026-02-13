package api;

import models.response.collection.RootGetCollectionsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CollectionService {
    @GET("/app/collections")
    Call<RootGetCollectionsResponse> getListCollections();
}
