import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {
    @Test
    public void changeProfileName() {
        String generatedName = generateRandomName();

        loginIntoApplication(validEmail, validPassword);
        openProfileSettings();
        enterCurrentPassword(validPassword);
        editProfileName(generatedName);
        saveProfile();
        WebElement profileName = driver.findElement(By.cssSelector(".view-profile>span"));
        Assert.assertEquals(profileName.getText(), generatedName);

        //Return original profile name (for future tests)
        enterCurrentPassword(validPassword);
        editProfileName(userName);
        saveProfile();
        Assert.assertEquals(profileName.getText(), userName);
    }
}
