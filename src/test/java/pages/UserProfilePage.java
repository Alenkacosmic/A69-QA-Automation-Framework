package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfilePage extends BasePage{
    public UserProfilePage(WebDriver givenDriver) { super(givenDriver); }

    @FindBy(id = "inputProfileCurrentPassword") WebElement currentPasswordField;
    @FindBy(id = "inputProfileName") WebElement profileNameField;
    @FindBy(css = ".btn-submit") WebElement saveProfileButton;
    @FindBy(xpath = "//div[@class='success show'][contains(text(),'Profile updated.')]") WebElement successNotification;
    @FindBy(css = ".view-profile>span") WebElement profileName;

    public void enterCurrentPassword(String currentPassword) {
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }
    public void editProfileName(String newProfileName) {
        profileNameField.sendKeys(Keys.CONTROL + "a");
        profileNameField.sendKeys(Keys.DELETE);
        profileNameField.sendKeys(newProfileName);
    }
    public void saveProfile() {
        saveProfileButton.click();
        successNotification.isDisplayed();
        wait.until(ExpectedConditions.invisibilityOf(successNotification));
    }

    public String getProfileName() {
        return profileName.getText();
    }
}
