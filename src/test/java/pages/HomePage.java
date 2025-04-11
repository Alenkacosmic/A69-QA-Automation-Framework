package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "img[class='avatar']") WebElement avatarIcon;
    @FindBy(css = "a[class='view-profile']") WebElement openUserProfileButton;

    public boolean isAvatarDisplayed() {
        return avatarIcon.isDisplayed();
    }

    public void openProfileSettings() {
        openUserProfileButton.click();
        verifySectionTitle("Profile & Preferences");
    }
}
