import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {
    @Test
    public void changeProfileName() {
        navigateToLoginPage();
        loginIntoApplication(validEmail, validPassword);
        //Click on avatar icon to open profile settings
        WebElement openProfileIcon = driver.findElement(By.cssSelector("a[class='view-profile']"));
        openProfileIcon.click();
        //Verify profile settings opened
        verifySectionTitle("Profile & Preferences");
        //Generate random name to be entered
        String generatedName = generateRandomName();
        //Enter current password for changes to be saved
        enterCurrentPassword(validPassword);
        editProfileName(generatedName);
        saveProfile();
        //Verify changes saved
        try {
            Thread.sleep(2000); //Added try/catch since have latest setting with java forbade sleep without throw or catch
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement profileName = driver.findElement(By.cssSelector(".view-profile>span"));
        Assert.assertEquals(profileName.getText(), generatedName);

        //Return original profile name (for future tests)
        enterCurrentPassword(validPassword);
        editProfileName(userName);
        saveProfile();
        try {
            Thread.sleep(2000); //Added try/catch since have latest setting with java forbade sleep without throw or catch
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(profileName.getText(), userName);
    }
}
