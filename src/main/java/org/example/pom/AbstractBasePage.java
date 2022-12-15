package org.example.pom;

import org.openqa.selenium.WebDriver;

abstract public class AbstractBasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}

