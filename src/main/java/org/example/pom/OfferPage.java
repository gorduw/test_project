package org.example.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OfferPage extends AbstractBasePage {

    @FindBy(xpath = "//button[contains(., 'Get Trial')]")
    private WebElement getTrialBtn;

    public OfferPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
