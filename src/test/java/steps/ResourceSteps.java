package steps;

import api.ResourceService;
import client.ApiKeyClient;
import config.ConfigProvider;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import models.response.resource.GetResourceByIdResponse;
import models.response.resource.GetResourcesResponse;
import org.assertj.core.api.Assertions;
import retrofit2.Response;
import tests.BaseTest;
import util.RandomUtil;


public class ResourceSteps {
    private final BaseTest baseTest;
    private final ApiKeyClient apiKeyClient;
    private final ResourceService resourceService;
    private final RandomUtil random = new RandomUtil();

    public ResourceSteps(BaseTest baseTest) {
        this.baseTest = baseTest;
        apiKeyClient = new ApiKeyClient(getToken());
        resourceService = apiKeyClient.setService(ResourceService.class);
    }

    private String getToken() {
        return ConfigProvider.getToken();
    }

    @Step("Getting list resources")
    @SneakyThrows
    public Response<GetResourcesResponse> getResourceList() {
        return resourceService.getResourceList().execute();
    }

    @Step("Getting list resources on page")
    @SneakyThrows
    public Response<GetResourcesResponse> getResourceList(int page) {
        return resourceService.getResourceList(page).execute();
    }

    @Step("Getting list resources on page and per page")
    @SneakyThrows
    public Response<GetResourcesResponse> getResourceList(int page, int perPage) {
        return resourceService.getResourceList(page, perPage).execute();
    }

    @Step("Getting resource by id")
    @SneakyThrows
    public Response<GetResourceByIdResponse> getResourceById(int id) {
        return resourceService.getResourceById(id).execute();
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
