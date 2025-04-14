package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "img[class='avatar']") WebElement avatarIcon;
    @FindBy(css = "a[class='view-profile']") WebElement openUserProfileButton;
    @FindBy(css = ".logout.control") WebElement logoutButton;

    public boolean isAvatarDisplayed() {
        return avatarIcon.isDisplayed();
    }

    public void openProfileSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(openUserProfileButton)).click();
        verifySectionTitle("Profile & Preferences");
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}
