package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverHelper {

    private static DriverHelper instanceOfSingletonBrowserClass = null;

    private WebDriver driver;

    private DriverHelper() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        ChromeOptions options = new ChromeOptions();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.addArguments("start-maximized");
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);

    }

    public static DriverHelper getInstanceOfSingletonBrowserClass() {
        if (instanceOfSingletonBrowserClass == null) {
            instanceOfSingletonBrowserClass = new DriverHelper();
        }
        return instanceOfSingletonBrowserClass;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
