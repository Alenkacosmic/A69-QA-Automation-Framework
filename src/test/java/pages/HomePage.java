package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By avatarIcon = By.cssSelector("img[class='avatar']");

    public boolean isAvatarDisplayed() {
        return driver.findElement(avatarIcon).isDisplayed();
    }
}
