package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) { super(givenDriver); }

    @FindBy(css = "input[type='email']") WebElement emailField;
    @FindBy(css = "input[type='password']") WebElement passwordField;
    @FindBy(css = "button[type='submit']") WebElement submitLogin;
    @FindBy(partialLinkText = "Registration") WebElement registrationLink;

    public LoginPage fillInEmail(String userEmailAddress) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(userEmailAddress);
        return this;
    }

    public LoginPage fillInPassword(String userPassword) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(userPassword);
        return this;
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submitLogin)).click();
    }

    public boolean isSubmitButtonDisplayed() {
        return submitLogin.isDisplayed();
    }

    public void loginIntoApplication(String userEmailAddress, String userPassword) {
        fillInEmail(userEmailAddress).fillInPassword(userPassword).submitLogin();
    }

    public void openRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationLink)).click();
    }
}
