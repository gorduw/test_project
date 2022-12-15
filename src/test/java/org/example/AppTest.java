package org.example;


import io.qameta.allure.Description;
import org.example.components.PaymentMenu;
import org.example.components.SignInMenu;
import org.example.pojo.User;
import org.example.pom.ExpertChatPage;
import org.example.pom.MainPage;
import org.example.utils.AllureLogger;
import org.example.utils.ConfigProvider;
import org.example.utils.Constants;
import org.example.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AppTest extends AbstractTest {


    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Description("Testing of coin purchase and verifying amount after")
    public void testCoinPurchaseFlowValid() {
        Integer startPoints;
        Integer endPoints;

        MainPage mainPage = new MainPage();
        SignInMenu signInMenu = mainPage.getSignInMenu();
        Assert.assertTrue(signInMenu.isModalDisplayed());
        ExpertChatPage expertChatPage = signInMenu.signUp(valid_user.getEmail());
        Assert.assertTrue(expertChatPage.isProfileBtnEnabled());
        startPoints = Integer.valueOf(expertChatPage.getPointAmount());
        PaymentMenu paymentMenu = expertChatPage.proceedToPayment();
        paymentMenu.buyCoinsFlow(valid_user.getCard(),
                valid_user.getCardDate(),
                valid_user.getCvv(),
                valid_user.getZip());

        expertChatPage = paymentMenu.submitPayment();

        Assert.assertTrue(expertChatPage.isSuccessImageDisplayed(), "Success image isn't on the page");
        expertChatPage.clickContinueBtn();

        endPoints = Integer.valueOf(expertChatPage.getPointAmount());
        // TODO add exactly amount to pay for and check if (startPoints + paidPoints = endPoints)
        System.out.printf("MIN" + startPoints + " MAX" + endPoints);
        AllureLogger.logToAllure("Start point: " + startPoints + "\b Points after payment: " + endPoints);
        Assert.assertTrue((startPoints < endPoints), "Points not added");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Description("Testing of declining restricted card")
    public void testCoinPurchaseFlowRestrictedCard() {
        MainPage mainPage = new MainPage();
        SignInMenu signInMenu = mainPage.getSignInMenu();
        Assert.assertTrue(signInMenu.isModalDisplayed());
        ExpertChatPage expertChatPage = signInMenu.signUp(valid_user.getEmail());
        Assert.assertTrue(expertChatPage.isProfileBtnEnabled());
        PaymentMenu paymentMenu = expertChatPage.proceedToPayment();
        paymentMenu.buyCoinsFlow(valid_user.getCard(),
                not_valid_user.getCardDate(),
                not_valid_user.getCvv(),
                not_valid_user.getZip());

        paymentMenu.submitPayment();
        paymentMenu.isRestrictedCardAlertPresented();
    }

    @Test
    @Description("Testing of not valid email response")
    public void testSignUpFlowNotValidEmail() {
        MainPage mainPage = new MainPage();

        SignInMenu signInMenu = mainPage.getSignInMenu();
        Assert.assertTrue(signInMenu.isModalDisplayed());
        Assert.assertTrue(signInMenu.isEmailInvalid(not_valid_user.getEmail()),
                "Notification about invalid email not presented");
        Assert.assertTrue(signInMenu.isContinueButtonDisabled(), "Continue button enabled with not valid email");
    }

    @Test
    @Description("Testing of presence for text on the page")
    public void testTextVerificationMainPage() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isTextPresentedOnThePage(Constants.expertChatIsASupreme), "'Expert chat is a supreme' text not found");
        Assert.assertTrue(mainPage.isTextPresentedOnThePage(Constants.instantHelp), "'Instant help' text not found");
        Assert.assertTrue(mainPage.isTextPresentedOnThePage(Constants.useExpertChat), "'Use expert chat' text not found");
        Assert.assertTrue(mainPage.isTextPresentedOnThePage(Constants.inASafeChat), "'In a sage chat' text not found");
    }

    @Test
    @Description("Testing of sending message to expert chat")
    public void testMessageToChat() {
        String xpathTemp = "//button[contains(., '%s')]";
        String createNewTask = "Create new task";
        String askAQuestionAboutYourTask = "Ask a question about your task";
        String messageTest = "Test message";
        MainPage mainPage = new MainPage();
        SignInMenu signInMenu = mainPage.getSignInMenu();
        ExpertChatPage expertChatPage = signInMenu.signUp(valid_user.getEmail());

        expertChatPage.sendNewTask(messageTest);

        /*
        try {
            if (driver.findElement(By.xpath(String.format(xpathTemp, createNewTask))).getSize() != null) {
                expertChatPage.sendNewTask(messageTest);
            }
        }
        catch(NoSuchElementException e) {
            System.out.println("Some of the buttons aren't on the page");
        }

        try {
            expertChatPage.sendMessage(messageTest);
        }
        catch(NoSuchElementException e) {
            System.out.println("Some of the buttons aren't on the page");
        }

        try {
          if (driver.findElement(By.xpath(String.format(xpathTemp, askAQuestionAboutYourTask))).getSize() != null) {
              expertChatPage.sendAnotherTaskQuestion(messageTest);
            }
        }
        catch(NoSuchElementException e) {
            System.out.println("Some of the buttons aren't on the page");
        } */

        Assert.assertTrue(expertChatPage.isMessagePresented(messageTest));
    }

    @Test
    @Description("Testing of sign in button in modal with different data")
    public void testSignInBtn() {
        MainPage mainPage = new MainPage();
        SignInMenu signInMenu = mainPage.getSignInMenu();

        signInMenu.setLoginData(valid_user.getEmail());
        signInMenu.setPasswordData(valid_user.getPassword());
        Assert.assertFalse(signInMenu.isSignInButtonDisabled(), "Sign In button disabled with valid data");

        signInMenu.setLoginData(not_valid_user.getEmail());
        signInMenu.setPasswordData(not_valid_user.getPassword());
        Assert.assertTrue(signInMenu.isSignInButtonDisabled(), "Sign In button enabled with not valid data");
    }


    private User valid_user = User.builder()
            .email(ConfigProvider.EMAIL_VALID)
            .password(ConfigProvider.PASSWORD_VALID)
            .card(ConfigProvider.CARD_VALID)
            .cardDate(ConfigProvider.CARDDATE_VALID)
            .cvv(ConfigProvider.CVV_VALID)
            .zip(ConfigProvider.ZIP_VALID)
            .build();

    private User not_valid_user = User.builder()
            .email(ConfigProvider.EMAIL_NOT_VALID)
            .password(ConfigProvider.PASSWORD_NOT_VALID)
            .card(ConfigProvider.CARD_NOT_VALID)
            .cardDate(ConfigProvider.CARDDATE_NOT_VALID)
            .cvv(ConfigProvider.CVV_NOT_VALID)
            .zip(ConfigProvider.ZIP_NOT_VALID)
            .build();

}



