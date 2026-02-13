package tests;

import models.response.collection.RootGetCollectionsResponse;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

public class CollectionTest extends BaseTest {
    @Test
    public void getListCollectionsTest() throws IOException {
        Response<RootGetCollectionsResponse> response = collectionStep.getListCollections();
        if (response.code() == 429) System.out.println(response.headers());
        collectionStep.checkResponseIsSuccessful(response);
    }

}
