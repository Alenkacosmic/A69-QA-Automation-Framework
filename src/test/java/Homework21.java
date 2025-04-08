import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework21 extends BaseTest{
    String playlistName = generateRandomPlaylistName();
    String newPlaylistName = generateRandomPlaylistName();

    @Test
    public void renamePlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        createNewPlaylist(playlistName);
        verifyPlaylistTitle(playlistName);
        renamePlaylistWithDoubleClick(playlistName, newPlaylistName);
        verifyPlaylistTitle(newPlaylistName);
        deleteEmptyPlaylist();
    }

    @Test
    public void renamePlaylistThroughMenu() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        createNewPlaylist(playlistName);
        verifyPlaylistTitle(playlistName);
        renamePlaylistUsingMenu(playlistName, newPlaylistName);
        verifyPlaylistTitle(newPlaylistName);
        deleteEmptyPlaylist();
    }
}
