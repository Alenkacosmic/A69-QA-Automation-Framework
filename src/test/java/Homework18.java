import org.testng.annotations.Test;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class Homework18 extends BaseTest {
    @Test
    public void playSong() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        clickNextSong();
        clickPlaySong();
        Assert.assertTrue(isSongPlaying(), "Song is not playing");
    }
}
