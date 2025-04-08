import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework19 extends BaseTest {

    @Test
    public void deletePlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        String playlistName = generateRandomPlaylistName();

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        createNewPlaylist(playlistName);
        deleteEmptyPlaylist();
        successNotificationDisplayed("Delete", playlistName);
    }
}
