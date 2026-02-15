package tests;

import constants.ApiConstants;
import models.request.collection.CreateSchemaRequest;
import models.response.collection.RootCreateRecordResponse;
import models.response.collection.RootGetCollectionBySlugResponse;
import models.response.collection.RootGetCollectionsResponse;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import retrofit2.Response;
import tests.steps.AppUserSteps;
import tests.steps.CollectionSteps;
import util.TokenUtil;

import java.io.IOException;

public class BaseTest {
    protected static AppUserSteps appUserStep;
    protected static CollectionSteps collectionStep;

    @BeforeSuite
    public void createCollection() throws IOException {
        appUserStep = new AppUserSteps();
        collectionStep = new CollectionSteps();

        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();
        collectionStep.getApiKeyClient().setBearerToken(token);

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

    @BeforeSuite
    public void createRecords() throws IOException {
        appUserStep = new AppUserSteps();
        collectionStep = new CollectionSteps();

        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();
        collectionStep.getApiKeyClient().setBearerToken(token);

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

    @BeforeClass
    public void setUp() throws IOException {
        appUserStep = new AppUserSteps();
        collectionStep = new CollectionSteps();

        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();
        appUserStep.getApiKeyClient().setBearerToken(token);
        collectionStep.getApiKeyClient().setBearerToken(token);
    }

    @AfterSuite
    public void deleteCollection() throws IOException {
        appUserStep = new AppUserSteps();
        collectionStep = new CollectionSteps();

        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();
        collectionStep.getApiKeyClient().setBearerToken(token);

        String slug = ApiConstants.getSlug();

        Response<Void> response = collectionStep.deleteCollection(slug);
        if (response.code() == 204) System.out.println(response.headers());
        collectionStep.checkResponseIsSuccessful(response);
    }
}
