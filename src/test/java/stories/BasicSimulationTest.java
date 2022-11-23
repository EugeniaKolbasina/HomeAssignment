package stories;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import enums.DatasetType;
import framework.BasePage;
import framework.BaseTest;
import pages.HomePage;

public class BasicSimulationTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void openHomePage() {
        homePage = BasePage.initPage(HomePage.class);
    }

    @Test
    public void basicSimulationTest() {
        System.out.println("Test loss by default is " + homePage.getTestLossValue());
        homePage.selectDataset(DatasetType.EXCLUSIVE_OR);
        homePage.selectNoisePercentage(5);
        homePage.selectX1SquaredFeature();
        homePage.selectX2SquaredFeature();
        homePage.removeNeuronFromHiddenLayer(1);
        homePage.removeNeuronFromHiddenLayer(2);
        homePage.selectLearningRate("0.1");
        homePage.runSimulation();
        homePage.waitForEpochToBeMoreThan(0.3);
        System.out.println("Test loss after change of parameters and simulation is " + homePage.getTestLossValue());
    }

}
