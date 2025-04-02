import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    @Test
    public void renamePLaylist() {
        String playlistName = "First Playlist Name";
        String newPlaylistName = "Updated Playlist Name";

        loginIntoApplication(validEmail, validPassword);
        createNewPlaylist(playlistName);
        verifySectionTitle(playlistName);
        renamePlaylist(playlistName, newPlaylistName);
        verifySectionTitle(newPlaylistName);
        deleteEmptyPlaylist();
    }
}
