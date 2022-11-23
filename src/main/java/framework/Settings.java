package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static final String SELENIUM_PROPERTIES = "selenium.properties";
    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_CHROME_DRIVER = "selenium.chromeDriver";
    private String baseUrl;
    private String chromeDriver;
    private Properties properties = new Properties();

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        properties = loadPropertiesFile();
        baseUrl = getProperty(SELENIUM_BASEURL);
        chromeDriver = getProperty(SELENIUM_CHROME_DRIVER);
    }

    private Properties loadPropertiesFile() {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(SELENIUM_PROPERTIES);
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Properties file is not found");
        }
    }

    private String getProperty(String propertyName) {
        Object propertyValue = properties.get(propertyName);
        if (propertyValue != null && propertyValue.toString().length() > 0) {
            return propertyValue.toString();
        } else if (propertyValue != null) {
            throw new UnknownPropertyException("Empty value of [" + propertyName + "] property");
        } else {
            throw new UnknownPropertyException("Unknown property name: [" + propertyName + "]");
        }
    }

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        return new ChromeDriver();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
