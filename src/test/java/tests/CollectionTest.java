package tests;

import constants.ApiConstants;
import models.request.collection.CreateSchemaRequest;
import models.response.collection.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;
import util.TokenUtil;

import java.io.IOException;

public class CollectionTest extends BaseTest {
    @BeforeClass
    public void setUp() throws IOException {
        this.setSteps();
        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();
        collectionStep.getApiKeyClient().setBearerToken(token);

        createCollection();
        createRecords();
    }

    private void createCollection() throws IOException {
        Response<RootGetCollectionsResponse> responseListCollections = collectionStep.getListCollections();
        collectionStep.checkResponseIsSuccessful(responseListCollections);
        RootGetCollectionsResponse responseListCollectionsBody = collectionStep.checkResponseBodyNotNull(responseListCollections);

        if (!responseListCollectionsBody.getData().isEmpty()) {
            return;
        }

        String name = ApiConstants.getCollectionName();
        String slug = ApiConstants.getSlug();
        CreateSchemaRequest schema = new CreateSchemaRequest("message");
        String projectId = ApiConstants.getProjectId();
        String visibility = "public";

        Response<RootGetCollectionBySlugResponse> response = collectionStep.createCollection(name, slug, schema, projectId, visibility);
        collectionStep.checkResponseIsSuccessful(response);
    }

    private void createRecords() throws IOException {
        String slug = ApiConstants.getSlug();
        String messageFirst = "example_1";
        String messageSecond = "example_2";
        String messageThird = "example_3";

        Response<RootCreateRecordResponse> responseFirst = collectionStep.createRecord(slug, messageFirst);
        collectionStep.checkResponseIsSuccessful(responseFirst);
        Response<RootCreateRecordResponse> responseSecond = collectionStep.createRecord(slug, messageSecond);
        collectionStep.checkResponseIsSuccessful(responseSecond);
        Response<RootCreateRecordResponse> responseThird = collectionStep.createRecord(slug, messageThird);
        collectionStep.checkResponseIsSuccessful(responseThird);
    }

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
        if (response.code() == 200) System.out.println(response.headers());
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

    @Test
    public void getRecordByIdTest() throws IOException {
        String slug = ApiConstants.getSlug();
        Response<RootGetListRecordsResponse> responseListRecords = collectionStep.getListRecords(slug);
        collectionStep.checkResponseIsSuccessful(responseListRecords);
        RootGetListRecordsResponse responseListRecordsBody = collectionStep.checkResponseBodyNotNull(responseListRecords);
        String recordId = collectionStep.generateRecordId(responseListRecordsBody);

        Response<RootCreateRecordResponse> responseRecord = collectionStep.getRecordById(slug, recordId);
        collectionStep.checkResponseIsSuccessful(responseRecord);
    }

    @Test
    public void updateRecordTest() throws IOException {
        String slug = ApiConstants.getSlug();
        String newMessage = "new message";
        Response<RootGetListRecordsResponse> responseListRecords = collectionStep.getListRecords(slug);
        collectionStep.checkResponseIsSuccessful(responseListRecords);
        RootGetListRecordsResponse responseListRecordsBody = collectionStep.checkResponseBodyNotNull(responseListRecords);
        String recordId = collectionStep.generateRecordId(responseListRecordsBody);

        Response<RootCreateRecordResponse> responseUpdateRecord = collectionStep.updateRecord(slug, recordId, newMessage);
        collectionStep.checkResponseIsSuccessful(responseUpdateRecord);
        RootCreateRecordResponse responseUpdateRecordBody = collectionStep.checkResponseBodyNotNull(responseUpdateRecord);

        Response<RootCreateRecordResponse> responseRecord = collectionStep.getRecordById(slug, recordId);
        collectionStep.checkResponseIsSuccessful(responseRecord);
        RootCreateRecordResponse responseRecordBody = collectionStep.checkResponseBodyNotNull(responseRecord);

        collectionStep.checkUpdateRecord(responseUpdateRecordBody, responseRecordBody);
    }

    @Test
    public void deleteRecordTest() throws IOException {
        String slug = ApiConstants.getSlug();
        Response<RootGetListRecordsResponse> responseListRecords = collectionStep.getListRecords(slug);
        collectionStep.checkResponseIsSuccessful(responseListRecords);
        RootGetListRecordsResponse responseListRecordsBody = collectionStep.checkResponseBodyNotNull(responseListRecords);
        String recordId = collectionStep.generateRecordId(responseListRecordsBody);

        Response<Void> responseDeleteRecord = collectionStep.deleteRecord(slug, recordId);
        collectionStep.checkResponseIsSuccessful(responseDeleteRecord);

        Response<RootCreateRecordResponse> responseDeletedRecord = collectionStep.getRecordById(slug, recordId);
        collectionStep.checkDeleteRecord(responseDeletedRecord);
    }

    @AfterSuite
    public void deleteCollection() throws IOException {
        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();
        collectionStep.getApiKeyClient().setBearerToken(token);

        String slug = ApiConstants.getSlug();

        Response<Void> response = collectionStep.deleteCollection(slug);
        if (response.code() == 204) System.out.println(response.headers());
        collectionStep.checkResponseIsSuccessful(response);
    }
}
