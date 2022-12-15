package org.example.pom;

import lombok.SneakyThrows;
import org.example.components.SignInMenu;
import org.example.utils.AllureLogger;
import org.example.utils.ConfigProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractBasePage {

    @FindBy(xpath = "//*[@class=\"btn btn-primary px-5 py-3\"]")
    private WebElement signInBtn;

    public MainPage() {
        driver.get(ConfigProvider.URL_BASE);
        PageFactory.initElements(driver, this);
    }

    public SignInMenu getSignInMenu() {
        signInBtn.click();
        AllureLogger.logToAllure("Sign in button clicked");
        return new SignInMenu(driver);
    }

    @SneakyThrows
    public boolean isTextPresentedOnThePage(String text) {
        Thread.sleep(300);
        return driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]")).getSize() != null;
    }
}
