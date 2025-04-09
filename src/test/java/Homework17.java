import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework17 extends BaseTest {
    String songName = "BornKing";
    String playlistName = generateRandomPlaylistName();


    @Test
    public void addSongToPlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());

        createNewPlaylist(playlistName);
        searchForSong(songName);
        verifySuccessfulSearchResults(songName);
        viewAllSearchResults();
        selectSong(songName);
        addSongToExistingPlaylist(playlistName);
        successNotificationDisplayed("Add", playlistName);
        openExistingPlaylist(playlistName);
        deleteNonEmptyPlaylist();
        successNotificationDisplayed("Delete", playlistName);
    }

    @Test
    public void addSongAndPlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());

        searchForSong(songName);
        verifySuccessfulSearchResults(songName);
        viewAllSearchResults();
        selectSong(songName);
        addSongToNewPlaylist(playlistName);
        successNotificationDisplayed("Create", playlistName);
        verifySectionTitle(playlistName);
        deleteNonEmptyPlaylist();
        successNotificationDisplayed("Delete", playlistName);
    }
}
