package org.example;

import org.example.pom.AbstractBasePage;
import org.example.utils.AllureLogger;
import org.example.utils.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

abstract public class AbstractTest {
    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = DriverHelper.getInstanceOfSingletonBrowserClass().getDriver();
        AbstractBasePage.setDriver(driver);
        AllureLogger.logToAllure("Driver is set");
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        AllureLogger.logToAllure("Driver closed");
        driver.quit();
        AllureLogger.logToAllure("Browser closed");
    }
}
