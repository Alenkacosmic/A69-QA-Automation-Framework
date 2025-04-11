import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.Playlists;

public class Homework21 extends BaseTest{
    String playlistName = generateRandomPlaylistName();
    String newPlaylistName = generateRandomPlaylistName();

    @Test
    public void renamePlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        Playlists playlists = new Playlists(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());

        playlists.createNewPlaylist(playlistName);
        playlists.verifyPlaylistTitle(playlistName);
        playlists.renamePlaylistWithDoubleClick(playlistName, newPlaylistName);
        playlists.successNotificationIsDisplayed("Update", newPlaylistName);
        playlists.verifyPlaylistTitle(newPlaylistName);
        playlists.deleteEmptyPlaylist();
    }

    @Test
    public void renamePlaylistThroughMenu() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        Playlists playlists = new Playlists(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());

        playlists.createNewPlaylist(playlistName);
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Create", playlistName));

        playlists.verifyPlaylistTitle(playlistName);
        playlists.renamePlaylistUsingMenu(playlistName, newPlaylistName);
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Update", newPlaylistName));
        playlists.verifyPlaylistTitle(newPlaylistName);
        playlists.deleteEmptyPlaylist();
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Delete", newPlaylistName));
    }
}
