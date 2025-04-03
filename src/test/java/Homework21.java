import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    String playlistName = "First Playlist Name";
    String newPlaylistName = "Updated Playlist Name";

    @Test
    public void renamePlaylist() {
        loginIntoApplication(validEmail, validPassword);
        createNewPlaylist(playlistName);
        verifySectionTitle(playlistName);
        renamePlaylistWithDoubleClick(playlistName, newPlaylistName);
        verifySectionTitle(newPlaylistName);
        deleteEmptyPlaylist();
    }

    @Test
    public void renamePlaylistThroughMenu() {
        loginIntoApplication(validEmail, validPassword);
        createNewPlaylist(playlistName);
        verifySectionTitle(playlistName);
        renamePlaylistUsingMenu(playlistName, newPlaylistName);
        verifySectionTitle(newPlaylistName);
        deleteEmptyPlaylist();
    }
}
