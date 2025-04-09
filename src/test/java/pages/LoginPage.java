package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitLogin = By.cssSelector("button[type='submit']");

    public void fillInEmail(String userEmailAddress) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(userEmailAddress);
    }

    public void fillInPassword(String userPassword) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submitLogin));
        driver.findElement(submitLogin).click();
    }

    public boolean isSubmitButtonDisplayed() {
        return driver.findElement(submitLogin).isDisplayed();
    }

    public void loginIntoApplication(String userEmailAddress, String userPassword) {
        fillInEmail(userEmailAddress);
        fillInPassword(userPassword);
        submitLogin();
    }
}
