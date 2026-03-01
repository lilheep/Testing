package steps;

import api.CollectionService;
import client.ApiKeyClient;
import config.ConfigProvider;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.SneakyThrows;
import models.request.collection.*;
import models.response.collection.RootCreateRecordResponse;
import models.response.collection.RootGetCollectionBySlugResponse;
import models.response.collection.RootGetCollectionsResponse;
import models.response.collection.RootGetListRecordsResponse;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import tests.BaseTest;
import util.RandomUtil;


public class CollectionSteps {
    private final BaseTest baseTest;
    @Getter
    private final ApiKeyClient apiKeyClient;
    private final CollectionService collectionService;
    private final RandomUtil random = new RandomUtil();

    public CollectionSteps(BaseTest baseTest) {
        this.baseTest = baseTest;
        apiKeyClient = new ApiKeyClient(getToken());
        collectionService = apiKeyClient.setService(CollectionService.class);
    }

    private String getToken() {
        return ConfigProvider.getToken();
    }

    @Step("Create collection")
    @SneakyThrows
    public Response<RootGetCollectionBySlugResponse> createCollection(
            String name, String slug, CreateSchemaRequest schema, String projectId, String visibility) {
        return collectionService.createCollection(new CreateCollectionRequest(
                name, slug, schema, projectId, visibility
        )).execute();
    }

    @Step("Delete collection by slug")
    @SneakyThrows
    public Response<Void> deleteCollection(String slug) {
        return collectionService.deleteCollection(slug).execute();
    }

    @Step("Update collection")
    @SneakyThrows
    public Response<RootGetCollectionBySlugResponse> updateCollection(String slug, String visibility) {
        return collectionService.updateCollection(slug, new UpdateCollectionRequest(visibility)).execute();
    }

    @Step("Get list collections")
    @SneakyThrows
    public Response<RootGetCollectionsResponse> getListCollections() {
        return collectionService.getListCollections().execute();
    }

    @Step("Get collection by slug")
    @SneakyThrows
    public Response<RootGetCollectionBySlugResponse> getCollectionBySlug(String slug) {
        return collectionService.getCollectionBySlug(slug).execute();
    }

    @Step("Create record")
    @SneakyThrows
    public Response<models.response.collection.RootCreateRecordResponse> createRecord(String slug, String message) {
        return collectionService.createRecord(slug, new RootCreateRecordRequest(
                new CreateRecordRequest(message)
        )).execute();
    }

    @Step("Get list records")
    @SneakyThrows
    public Response<RootGetListRecordsResponse> getListRecords(String slug){
        return collectionService.getListRecords(slug).execute();
    }

    @Step("Get list records on limit")
    @SneakyThrows
    public Response<RootGetListRecordsResponse> getListRecords(String slug, int limit) {
        return collectionService.getListRecords(slug, limit).execute();
    }

    @Step("Get record by id")
    @SneakyThrows
    public Response<RootCreateRecordResponse> getRecordById(String slug, String recordId) {
        return collectionService.getRecordById(slug, recordId).execute();
    }

    @Step("Update record")
    @SneakyThrows
    public Response<RootCreateRecordResponse> updateRecord(String slug, String recordId, String newMessage) {
        return collectionService.updateRecord(slug, recordId,
                new RootUpdateRecordRequest(
                        new UpdateRecordRequest(
                                new RecordRequest(newMessage)
                        )
                )
        ).execute();
    }

    @Step("Delete record")
    @SneakyThrows
    public Response<Void> deleteRecord(String slug, String recordId) {
        return collectionService.deleteRecord(slug, recordId).execute();
    }

    @Step("Checking success response")
    public void checkResponseIsSuccessful(Response<?> response) {
        Assertions.assertThat(response.isSuccessful())
                .withFailMessage("Incorrect status code: " + response.code())
                .isTrue();
    }

    @Step("Check response body not null")
    public <T> T checkResponseBodyNotNull(Response<T> response) {
        T responseBody = response.body();
        Assertions.assertThat(responseBody)
                .withFailMessage("Response body is null")
                .isNotNull();
        return responseBody;
    }

    @Step("Check get collection by slug")
    public void checkCollectionBySlug(RootGetCollectionBySlugResponse responseBody, String slug) {
        Assertions.assertThat(responseBody.getData().getSlug())
                .withFailMessage("Slug not equal slug in response")
                .isEqualTo(slug);
    }

    @Step("Generate random record id")
    public String generateRecordId(RootGetListRecordsResponse responseBody) {
        return random.generateRecordId(responseBody);
    }

    @Step("Check update collection")
    public void checkUpdateCollection(RootGetCollectionBySlugResponse responseUpdateBody,
                                      RootGetCollectionBySlugResponse responseCollectionBody,
                                      String visibility) {
        Assertions.assertThat(responseUpdateBody.getData().getVisibility())
                .withFailMessage("Collection no update")
                .isEqualTo(visibility);

        Assertions.assertThat(responseUpdateBody.getData().getVisibility())
                .withFailMessage("Collection no update")
                .isEqualTo(responseCollectionBody.getData().getVisibility());
    }

    @Step("Check list records on limit")
    public void checkListRecordsOnLimit(RootGetListRecordsResponse responseBody, int limit) {
        Assertions.assertThat(responseBody.getMeta().getLimit())
                .withFailMessage("Not equal limit")
                .isEqualTo(limit);
        Assertions.assertThat(responseBody.getData().size())
                .withFailMessage("Count records not equal limit")
                .isEqualTo(limit);
    }

    @Step("Check update record")
    public void checkUpdateRecord(RootCreateRecordResponse responseUpdateRecordBody,
                                  RootCreateRecordResponse responseRecordBody) {
        Assertions.assertThat(responseUpdateRecordBody.getData().getData().getMessage())
                .withFailMessage("Message not update")
                .isEqualTo(responseRecordBody.getData().getData().getMessage());
    }

    @Step("Check delete record")
    public void checkDeleteRecord(Response<RootCreateRecordResponse> responseRecord) {
        Assertions.assertThat(responseRecord.code())
                .withFailMessage("Record no deleted")
                .isEqualTo(404);
    }
}
