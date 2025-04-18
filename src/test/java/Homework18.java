import org.testng.annotations.Test;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.MusicPlayer;

public class Homework18 extends BaseTest {
    @Test
    public void playSong() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        MusicPlayer musicPlayer = new MusicPlayer(getDriver());

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        musicPlayer.clickNextSong();
        musicPlayer.clickPlaySong();
        Assert.assertTrue(musicPlayer.isSongPlaying(), "Song is not playing");
    }
}
