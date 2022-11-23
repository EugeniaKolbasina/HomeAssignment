package framework;

import org.testng.annotations.*;

public class BaseTest {
    private static final Settings settings = new Settings();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        BasePage.driver = settings.getDriver();
        BasePage.settings = settings;
        BasePage.driver.get(settings.getBaseUrl());
        BasePage.driver.manage().window().maximize();
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        BasePage.driver.quit();
    }
}
