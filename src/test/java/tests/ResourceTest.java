package tests;

import models.response.resource.GetResourceByIdResponse;
import models.response.resource.GetResourcesResponse;
import org.testng.annotations.Test;
import retrofit2.Response;
import tests.steps.ResourceSteps;

import java.io.IOException;

public class ResourceTest {
    private static final ResourceSteps resourceStep = new ResourceSteps();

    @Test
    public void getResourcesTest() throws IOException {
        int page = 1;
        int perPage = 6;
        int total = 12;
        Response<GetResourcesResponse> response = resourceStep.getResourceList();
        resourceStep.checkResponseIsSuccessful(response);

        GetResourcesResponse responseBody = resourceStep.checkResponseBodyNotNull(response);

        resourceStep.checkResourceList(responseBody, page, perPage, total);
    }

    @Test
    public void getResourcesOnPageTest() throws IOException {
        int page = 2;
        int perPage = 6;
        int total = 12;
        Response<GetResourcesResponse> response = resourceStep.getResourceList(page);
        resourceStep.checkResponseIsSuccessful(response);

        GetResourcesResponse responseBody = resourceStep.checkResponseBodyNotNull(response);

        resourceStep.checkResourceList(responseBody, page, perPage, total);
    }

    @Test
    public void getResourcesOnPageAndPerTest() throws IOException {
        int page = 3;
        int perPage = 4;
        int total = 12;
        Response<GetResourcesResponse> response = resourceStep.getResourceList(page, perPage);
        resourceStep.checkResponseIsSuccessful(response);

        GetResourcesResponse responseBody = resourceStep.checkResponseBodyNotNull(response);

        resourceStep.checkResourceList(responseBody, page, perPage, total);
    }

    @Test
    public void getResourceByIdTest() throws IOException {
        int id = resourceStep.generateValidId();
        Response<GetResourceByIdResponse> response = resourceStep.getResourceById(id);

        resourceStep.checkResponseIsSuccessful(response);

        GetResourceByIdResponse responseBody = resourceStep.checkResponseBodyNotNull(response);

        resourceStep.checkResourceById(responseBody, id);
    }
}
