import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserProfilePage;

public class ProfileTests extends BaseTest {
    @Test
    public void changeProfileName() {
        String generatedName = generateRandomUserName();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        UserProfilePage userProfile = new UserProfilePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        homePage.openProfileSettings();
        userProfile.enterCurrentPassword(validPassword);
        userProfile.editProfileName(generatedName);
        userProfile.saveProfile();
        Assert.assertEquals(userProfile.getProfileName(), generatedName);

        //Return original profile name
        userProfile.enterCurrentPassword(validPassword);
        userProfile.editProfileName(userName);
        userProfile.saveProfile();
        Assert.assertEquals(userProfile.getProfileName(), userName);
    }
}
