package tests.steps;

import api.ResourceService;
import api.UserService;
import client.ApiClient;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import constants.ApiConstants;
import io.qameta.allure.Step;
import models.response.resource.GetResourceByIdResponse;
import models.response.resource.GetResourcesResponse;
import models.response.resource.ResourceResponse;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import util.RandomUtil;

import java.io.IOException;

public class ResourceSteps {
    private static final ApiClient apiclient = new ApiClient();
    private static final ResourceService resourceService = apiclient.getResourceService();
    private final RandomUtil random = new RandomUtil();

    @Step("Getting list resources")
    public Response<GetResourcesResponse> getResourceList() throws IOException {
        return resourceService.getResourceList(ApiConstants.getToken()).execute();
    }

    @Step("Getting list resources on page")
    public Response<GetResourcesResponse> getResourceList(int page) throws IOException {
        return resourceService.getResourceList(ApiConstants.getToken(), page).execute();
    }

    @Step("Getting list resources on page and per page")
    public Response<GetResourcesResponse> getResourceList(int page, int perPage) throws IOException {
        return resourceService.getResourceList(ApiConstants.getToken(), page, perPage).execute();
    }

    @Step("Getting resource by id")
    public Response<GetResourceByIdResponse> getResourceById(int id) throws IOException {
        return resourceService.getResourceById(ApiConstants.getToken(), id).execute();
    }

    @Step("Generating valid user ID")
    public int generateValidId() {
        return random.generateValidId();
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

    @Step("Check resource list")
    public void checkResourceList(GetResourcesResponse responseBody, int page, int perPage, int total) {
        Assertions.assertThat(responseBody.getPage())
                .withFailMessage("Not equal page")
                .isEqualTo(page);

        Assertions.assertThat(responseBody.getPerPage())
                .withFailMessage("Not equal per page")
                .isEqualTo(perPage);

        Assertions.assertThat(responseBody.getTotal())
                .withFailMessage("Not equal total")
                .isEqualTo(total);

        Assertions.assertThat(responseBody.getData().size())
                .withFailMessage("Count resources not equal per page")
                .isEqualTo(perPage);
    }

    @Step("Check resource id")
    public void checkResourceById(GetResourceByIdResponse resourceResponse, int id) {
        Assertions.assertThat(resourceResponse.getData().getId())
                .withFailMessage("Not equal id")
                .isEqualTo(id);
    }





}
