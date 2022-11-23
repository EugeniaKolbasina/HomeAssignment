package pages;

import enums.DatasetType;
import framework.BasePage;
import framework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    private static final By TEST_LOSS_VALUE = By.id("loss-test");
    private static final String DATASET = "[data-dataset='%s']";
    private static final By NOISE_SLIDER = By.id("noise");
    private static final By X1_SQUARED_FEATURE = By.id("canvas-xSquared");
    private static final By X2_SQUARED_FEATURE = By.id("canvas-ySquared");
    private static final String REMOVE_NEURON_FROM_HIDDEN_LAYER_BUTTON = ".plus-minus-neurons .ui-numNodes%s .mdl-button";
    private static final By LEARNING_RATE_DROP_DOWN = By.id("learningRate");
    private static final By RUN_STOP_SIMULATION_BUTTON = By.id("play-pause-button");
    private static final By EPOCH_VALUE = By.id("iter-number");

    public String getTestLossValue() {
        return Utils.getText(TEST_LOSS_VALUE);
    }

    public void selectDataset(DatasetType datasetType) {
        driver.findElement(By.cssSelector(String.format(DATASET, datasetType))).click();
    }

    public void selectNoisePercentage(int percentage) {
        WebElement slider = driver.findElement(NOISE_SLIDER);
        for (int i = 0; i < percentage / 5; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public void selectX1SquaredFeature() {
        driver.findElement(X1_SQUARED_FEATURE).click();
    }

    public void selectX2SquaredFeature() {
        driver.findElement(X2_SQUARED_FEATURE).click();
    }

    public void removeNeuronFromHiddenLayer(int layerNumber) {
        WebElement removeButton = driver.findElements(
                By.cssSelector(String.format(REMOVE_NEURON_FROM_HIDDEN_LAYER_BUTTON, layerNumber))).get(1);
        removeButton.click();
    }

    public void selectLearningRate(String learningRate) {
        Select learningRateDropDown = new Select(driver.findElement(LEARNING_RATE_DROP_DOWN));
        learningRateDropDown.selectByValue(learningRate);
    }

    public void runSimulation() {
        driver.findElement(RUN_STOP_SIMULATION_BUTTON).click();
    }

    public void waitForEpochToBeMoreThan(double threshold) {
        WebElement epochValueElement = driver.findElement(EPOCH_VALUE);
        double epochValue = 0.0;
        while (epochValue <= threshold) {
            String epochValueText = epochValueElement.getText();
            epochValue = Double.parseDouble(formatEpochValueText(epochValueText).replace(",", "."));
        }

    }

    private String formatEpochValueText(String epochValueText) {
        if (epochValueText.startsWith("00")) {
            return epochValueText.substring(2);
        } else if (epochValueText.startsWith("0")) {
            return epochValueText.substring(1);
        } else {
            return epochValueText;
        }
    }

}
