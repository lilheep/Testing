package tests;

import constants.ApiConstants;
import models.response.collection.RootGetCollectionBySlugResponse;
import models.response.collection.RootGetCollectionsResponse;
import models.response.collection.RootGetListRecordsResponse;
import org.testng.annotations.Test;
import retrofit2.Response;
import util.TokenUtil;

import java.io.IOException;

public class CollectionTest extends BaseTest {
    @Test
    public void updateCollectionTest() throws IOException {
        String slug = ApiConstants.getSlug();
        String visibility = "private";
        Response<RootGetCollectionBySlugResponse> responseUpdateCollection = collectionStep.updateCollection(slug, visibility);
        collectionStep.checkResponseIsSuccessful(responseUpdateCollection);
        RootGetCollectionBySlugResponse responseUpdateBody = collectionStep.checkResponseBodyNotNull(responseUpdateCollection);

        Response<RootGetCollectionBySlugResponse> responseCollection = collectionStep.getCollectionBySlug(slug);
        collectionStep.checkResponseIsSuccessful(responseCollection);
        RootGetCollectionBySlugResponse responseCollectionBody = collectionStep.checkResponseBodyNotNull(responseCollection);

        collectionStep.checkUpdateCollection(responseUpdateBody, responseCollectionBody, visibility);
    }

    @Test
    public void getListCollectionsTest() throws IOException {
        Response<RootGetCollectionsResponse> response = collectionStep.getListCollections();
        collectionStep.checkResponseIsSuccessful(response);
    }

    @Test
    public void getCollectionBySlugTest() throws IOException {
        String slug = ApiConstants.getSlug();
        Response<RootGetCollectionBySlugResponse> response = collectionStep.getCollectionBySlug(slug);
        collectionStep.checkResponseIsSuccessful(response);
        RootGetCollectionBySlugResponse responseBody = collectionStep.checkResponseBodyNotNull(response);
        collectionStep.checkCollectionBySlug(responseBody, slug);
    }

    @Test
    public void getListRecordsTest() throws IOException {
        String slug = ApiConstants.getSlug();
        Response<RootGetListRecordsResponse> response = collectionStep.getListRecords(slug);
        collectionStep.checkResponseIsSuccessful(response);
    }

    @Test
    public void getListRecordsOnLimitTest() throws IOException {
        String slug = ApiConstants.getSlug();
        int limit = 1;
        Response<RootGetListRecordsResponse> response = collectionStep.getListRecords(slug, limit);
        collectionStep.checkResponseIsSuccessful(response);
        RootGetListRecordsResponse responseBody = collectionStep.checkResponseBodyNotNull(response);
        collectionStep.checkListRecordsOnLimit(responseBody, limit);
    }



}
