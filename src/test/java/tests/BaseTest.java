package tests;

import org.testng.annotations.BeforeClass;
import tests.steps.AppUserSteps;
import tests.steps.CollectionSteps;
import util.TokenUtil;

import java.io.IOException;

public class BaseTest {
    protected static AppUserSteps appUserStep;
    protected static CollectionSteps collectionStep;

    @BeforeClass
    public void setUp() throws IOException {
        appUserStep = new AppUserSteps();
        collectionStep = new CollectionSteps();

        TokenUtil.setToken(appUserStep);
        String token = TokenUtil.getToken();
        appUserStep.getApiKeyClient().setBearerToken(token);
        collectionStep.getApiKeyClient().setBearerToken(token);
    }
}
