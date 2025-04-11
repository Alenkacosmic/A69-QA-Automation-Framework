package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) { super(givenDriver); }

    @FindBy(css = "input[type='email']") WebElement emailField;
    @FindBy(css = "input[type='password']") WebElement passwordField;
    @FindBy(css = "button[type='submit']") WebElement submitLogin;
    @FindBy(partialLinkText = "Registration") WebElement registrationLink;

    public LoginPage fillInEmail(String userEmailAddress) {
        emailField.clear();
        emailField.sendKeys(userEmailAddress);
        return this;
    }

    public LoginPage fillInPassword(String userPassword) {
        passwordField.clear();
        passwordField.sendKeys(userPassword);
        return this;
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submitLogin));
        submitLogin.click();
    }

    public boolean isSubmitButtonDisplayed() {
        return submitLogin.isDisplayed();
    }

    public void loginIntoApplication(String userEmailAddress, String userPassword) {
        fillInEmail(userEmailAddress).fillInPassword(userPassword).submitLogin();
    }

    public void openRegistrationPage() {
        registrationLink.click();
    }
}
