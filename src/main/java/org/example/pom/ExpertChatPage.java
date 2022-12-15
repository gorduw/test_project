package org.example.pom;

import lombok.SneakyThrows;
import org.example.components.PaymentMenu;
import org.example.utils.AllureLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ExpertChatPage extends AbstractBasePage {

    @FindBy(xpath = "//a[contains(., 'Profile')]")
    private WebElement profileBtn;

    @FindBy(xpath = "//span[contains(., 'Bue coins')]/parent::button")
    private WebElement buyCoinsBtn;

    @FindBy(xpath = "//button[contains(., 'Continue')]")
    private WebElement continueBtn;

    @FindBy(xpath = "//img[@src='/assets/success.png']")
    private WebElement successPaymentImage;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/p/span[3]")
    private WebElement pointsAvailable;

    @FindBy(xpath = "//button[contains(.,'Send')]")
    private WebElement sendTaskBtn;

    @FindBy(id = "messageInput")
    private WebElement messageInput;

    @FindBy(xpath = "//button[@class='btn app-btn-yellow btn-outline']")
    private WebElement createNewTaskBtn;

    @FindBy(xpath = "//textarea[contains(@class, 'form-control att-desc__input px-4 py-5')]")
    private WebElement taskTextInput;

    @FindBy(xpath = "//button[contains(@class, 'btn btn-outline app-btn-black w-')")
    private WebElement sendQuestionTaskBtn;

    @FindBy(xpath = "//*[@class='btn btn-outline border-2 app-btn-black w-100']")
    private WebElement sendNewTaskBtn;

    @FindBy(xpath = "//*[@class='btn btn-outline border-2 app-btn-white w-100']")
    private WebElement onlyAnswerBtn;

    @FindBy(xpath = "//*[@class='btn btn-outline app-btn-yellow']")
    private WebElement markAsDoneBtn;

    @FindBy(xpath = "//*[@class='btn btn-outline app-btn-white mb-3']")
    private WebElement askQuestionAboutYourTask;


    public ExpertChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @SneakyThrows
    public boolean isProfileBtnEnabled() {
        Thread.sleep(1000);
        return profileBtn.isEnabled();
    }

    @SneakyThrows
    public PaymentMenu proceedToPayment() {
        Thread.sleep(2500);
        buyCoinsBtn.click();
        AllureLogger.logToAllure("Buy coins button clicked");
        Thread.sleep(3000);
        return new PaymentMenu(driver);
    }

    @SneakyThrows
    public boolean isSuccessImageDisplayed() {
        Thread.sleep(1500);
        return successPaymentImage.isDisplayed();
    }

    @SneakyThrows
    public String getPointAmount() {
        Thread.sleep(500);
        return pointsAvailable.getText();
    }

    @SneakyThrows
    public void clickContinueBtn() {
        continueBtn.click();
        AllureLogger.logToAllure("Continue button clicked");
        Thread.sleep(500);
    }

    @SneakyThrows
    public void sendMessage(String message) {
        Thread.sleep(1000);
        messageInput.sendKeys(message);
        AllureLogger.logToAllure("Message sent to chat: " + message);
        Thread.sleep(1000);
        sendTaskBtn.click();
        AllureLogger.logToAllure("Send task button clicked");
        Thread.sleep(1500);
    }

    @SneakyThrows
    public void sendNewTask(String task) {
        Thread.sleep(20000);
        createNewTaskBtn.click();
        AllureLogger.logToAllure("Create new task button clicked");
        Thread.sleep(300);
        taskTextInput.sendKeys(task);
        AllureLogger.logToAllure("Task input filled with message: " + task);
        Thread.sleep(300);
        sendTaskBtn.click();
        AllureLogger.logToAllure("Send task button clicked");
        Thread.sleep(500);
        onlyAnswerBtn.click();
        AllureLogger.logToAllure("Only answer button clicked");
        Thread.sleep(1000);
    }

    @SneakyThrows
    public void sendAnotherTaskQuestion(String task) {
        askQuestionAboutYourTask.click();
        AllureLogger.logToAllure("Ask question about your task button clicked");
        Thread.sleep(300);
        taskTextInput.sendKeys(task);
        AllureLogger.logToAllure("Task text input filled with: " + task);
        Thread.sleep(300);
        sendQuestionTaskBtn.click();
        AllureLogger.logToAllure("Send question task button clicked");
        Thread.sleep(1000);
    }


    public boolean isMessagePresented(String message) {
        String xpathTemp = "//div[contains(@class, 'message-text p-5 p-3') and contains(.//p, '%s')]";
        return driver.findElement(By.xpath(String.format(xpathTemp, message))).getSize() != null;
    }


}
