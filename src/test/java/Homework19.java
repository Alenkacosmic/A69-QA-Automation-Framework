import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.Playlists;

public class Homework19 extends BaseTest {

    @Test
    public void deletePlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        Playlists playlists = new Playlists(driver);

        String playlistName = generateRandomPlaylistName();

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        playlists.createNewPlaylist(playlistName);
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Create", playlistName));
        playlists.deleteEmptyPlaylist();
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Delete", playlistName));
    }
}
