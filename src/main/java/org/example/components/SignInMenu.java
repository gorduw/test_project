package org.example.components;

import lombok.SneakyThrows;
import org.example.pom.AbstractBasePage;
import org.example.pom.ExpertChatPage;
import org.example.pom.OfferPage;
import org.example.utils.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignInMenu extends AbstractBasePage {

    @FindBy(xpath = "//*[@class=\"modal-content\"]")
    private WebElement signInModal;

    @FindBy(xpath = "//*[@class=\"text-primary font-weight-bold\"]")
    private WebElement signUpBtn;

    @FindBy(xpath = "//div/button[contains(.,'Sign in')]")
    private WebElement signInBtn;

    @FindBy(xpath = "//*[@type=\"email\"]")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@type=\"password\"]")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(., 'Continue')]")
    private WebElement continueBtn;

    @FindBy(xpath = "//*[@class=\"form-control is-invalid\"]")
    private WebElement emailInvalid;

    @FindBy(xpath = "//*[@class='font-weight-bold text-primary']")
    private List<WebElement> loginData;

    @FindBy(xpath = "//*[@class='navbar-brand text-black nuxt-link-active']")
    private WebElement solvingProLogo;

    WebDriverWait driverWait;

    public SignInMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isModalDisplayed() {
        return signInModal.isDisplayed();
    }

    public boolean isContinueBtnEnables() {
        return !continueBtn.getAttribute("class").contains("disabled");
    }

    public OfferPage clickContinueBtn() {
        continueBtn.click();
        AllureLogger.logToAllure("Continue button clicked");
        return new OfferPage(driver);
    }

    @SneakyThrows
    public ExpertChatPage signUp(String email) {
        signUpBtn.click();
        AllureLogger.logToAllure("Sign Up button in Sign In menu clicked");
        Thread.sleep(500);
        emailInput.clear();
        AllureLogger.logToAllure("Sign Up cleared");
        Thread.sleep(500);
        emailInput.sendKeys(email);
        AllureLogger.logToAllure("Email input filled with " + email);
        continueBtn.click();
        AllureLogger.logToAllure("Continue button clicked");
        continueBtn.click();
        AllureLogger.logToAllure("Continue button clicked");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.switchTo().activeElement();
        Thread.sleep(500);
        continueBtn.click();
        AllureLogger.logToAllure("Continue button clicked");
        Thread.sleep(1500);
        //continueBtn.click();
        //Thread.sleep(1000);
        return new ExpertChatPage(driver);
    }

    @SneakyThrows
    public boolean isEmailInvalid(String email) {
        signUpBtn.click();
        AllureLogger.logToAllure("Sign Up button clicked");
        Thread.sleep(500);
        emailInput.sendKeys(email);
        AllureLogger.logToAllure("Email input filled with " + email);
        Thread.sleep(1000);
        return emailInvalid.isDisplayed();
    }

    @SneakyThrows
    public void setLoginData(String login) {
        emailInput.clear();
        AllureLogger.logToAllure("Email input cleared");
        Thread.sleep(500);
        emailInput.sendKeys(login);
        AllureLogger.logToAllure("Email input filled with: " + login);
        Thread.sleep(500);
    }

    @SneakyThrows
    public void setPasswordData(String password) {
        passwordInput.clear();
        AllureLogger.logToAllure("Password input cleared");
        passwordInput.sendKeys(password);
        AllureLogger.logToAllure("Password input filled with: " + password);
        Thread.sleep(500);
    }

    public boolean isSignInButtonDisabled() {
        return signInBtn.getAttribute("class").contains("disabled");
    }

    public boolean isContinueButtonDisabled() {
        return continueBtn.getAttribute("class").contains("disabled");
    }

}
