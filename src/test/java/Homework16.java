import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework16 extends BaseTest {
    @Test
    public void registrationNavigation() {
        String registrationUrl = "https://qa.koel.app/registration";

        WebElement registrationLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Registration")));
        registrationLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl); //Verify user on registration page
    }
}
