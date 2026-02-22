package tests;

import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.BeforeClass;
import tests.steps.AppUserSteps;
import tests.steps.CollectionSteps;
import tests.steps.ResourceSteps;
import tests.steps.UserSteps;

public class BaseTest {
    @Getter
    @Setter
    protected String tokenApp;

    @Getter
    @Setter
    protected String sessionToken;

    protected UserSteps userStep;
    protected ResourceSteps resourceStep;
    protected AppUserSteps appUserStep;
    protected CollectionSteps collectionStep;

    @BeforeClass
    void setSteps() {
        userStep = new UserSteps(this);
        resourceStep = new ResourceSteps(this);
        appUserStep = new AppUserSteps(this);
        collectionStep = new CollectionSteps(this);
    }
}
