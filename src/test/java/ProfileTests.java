import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class ProfileTests extends BaseTest {
    @Test
    public void changeProfileName() {
        String generatedName = generateRandomUserName();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        openProfileSettings();
        enterCurrentPassword(validPassword);
        editProfileName(generatedName);
        saveProfile();
        WebElement profileName = driver.findElement(By.cssSelector(".view-profile>span"));
        Assert.assertEquals(profileName.getText(), generatedName);

        //Return original profile name
        enterCurrentPassword(validPassword);
        editProfileName(userName);
        saveProfile();
        Assert.assertEquals(profileName.getText(), userName);
    }
}
