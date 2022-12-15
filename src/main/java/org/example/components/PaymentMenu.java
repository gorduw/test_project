package org.example.components;

import lombok.SneakyThrows;
import org.example.pom.AbstractBasePage;
import org.example.pom.ExpertChatPage;
import org.example.utils.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PaymentMenu extends AbstractBasePage {

    private final static String restrictedCard = "Restricted card";
    WebDriverWait driverWait;
    @FindBy(name = "cardNumber")
    private WebElement cardInput;
    @FindBy(xpath = "//*[@class='input_group expiry_date']/div/input")
    private WebElement cardDateInput;
    @FindBy(name = "cardCvv")
    private WebElement cvvInput;
    @FindBy(name = "zip")
    private WebElement zipInput;
    @FindBy(xpath = "//button[contains(., 'Continue')]")
    private WebElement continueBtn;
    @FindBy(name = "submitButton")
    private WebElement payBtn;
    @FindBy(name = "solid-payment-form-iframe")
    private WebElement paymentFrame;
    @FindBy(xpath = "//*[@id='alert']")
    private WebElement restrictedCardAlert;

    public PaymentMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @SneakyThrows
    public void buyCoinsFlow(String card, String cardDate, String cvv, Integer zip) {

        continueBtn.click();
        AllureLogger.logToAllure("Continue button clicked");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentFrame));
        cardInput.sendKeys(card);
        AllureLogger.logToAllure("Card number filled with " + card);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(1000);

        // TODO REFACTOR
        cardDateInput.sendKeys("0");
        Thread.sleep(1000);
        cardDateInput.sendKeys(cardDate);
        AllureLogger.logToAllure("Card date filled with " + cardDate);

        Thread.sleep(500);
        cvvInput.sendKeys(cvv);
        AllureLogger.logToAllure("Cvv filled with " + cvv);
        Thread.sleep(500);
        zipInput.sendKeys(zip.toString());
        AllureLogger.logToAllure("Zip filled with " + zip);
        Thread.sleep(500);
    }

    @SneakyThrows
    public ExpertChatPage submitPayment() {
        payBtn.submit();
        AllureLogger.logToAllure("Payment submit pressed");
        driver.switchTo().defaultContent();
        Thread.sleep(4500);
        return new ExpertChatPage(driver);
    }

    public boolean isRestrictedCardAlertPresented() {
        return (restrictedCardAlert.isDisplayed()) && (restrictedCardAlert.getText().equals(restrictedCard));
    }
}
