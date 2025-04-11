import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.Playlists;
import pages.Songs;

public class Homework17 extends BaseTest {
    String songName = "BornKing";
    String playlistName = generateRandomPlaylistName();


    @Test
    public void addSongToPlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        Playlists playlists = new Playlists(driver);
        Songs songs = new Songs(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());

        playlists.createNewPlaylist(playlistName);
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Create", playlistName));

        songs.searchForSong(songName);
        Assert.assertTrue(songs.isSongFound(songName));
        songs.viewAllSearchResults();
        songs.selectSong(songName);
        songs.addSongToExistingPlaylist(playlistName);
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Add", playlistName));
        playlists.openExistingPlaylist(playlistName);
        playlists.deleteNonEmptyPlaylist();
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Delete", playlistName));
    }

    @Test
    public void addSongAndPlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        Playlists playlists = new Playlists(driver);
        Songs songs = new Songs(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());

        songs.searchForSong(songName);
        Assert.assertTrue(songs.isSongFound(songName));
        songs.viewAllSearchResults();
        songs.selectSong(songName);
        songs.addSongToNewPlaylist(playlistName);
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Create", playlistName));
        playlists.verifyPlaylistTitle(playlistName);
        playlists.deleteNonEmptyPlaylist();
        Assert.assertTrue(playlists.successNotificationIsDisplayed("Delete", playlistName));
    }
}
