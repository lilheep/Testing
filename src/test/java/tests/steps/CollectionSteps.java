package tests.steps;

import api.CollectionService;
import client.ApiKeyClient;
import constants.ApiConstants;
import io.qameta.allure.Step;
import lombok.Getter;
import models.response.collection.RootGetCollectionsResponse;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import util.RandomUtil;

import java.io.IOException;

public class CollectionSteps {
    @Getter
    private final ApiKeyClient apiKeyClient = new ApiKeyClient(getToken());
    private final CollectionService collectionService = apiKeyClient.getCollectionService();
    private final RandomUtil random = new RandomUtil();

    private String getToken() { return ApiConstants.getToken(); }

    @Step("Get list collections")
    public Response<RootGetCollectionsResponse> getListCollections() throws IOException {
        return collectionService.getListCollections().execute();
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


}
