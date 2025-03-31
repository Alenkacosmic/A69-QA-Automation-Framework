import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest {
    String songName = "BornKing";
    String playlistName = "TestPlaylist";


    @Test
    public void addSongToPlaylist() {
        navigateToLoginPage();
        loginIntoApplication(validEmail, validPassword);
        //Create new playlist (to be deleted later)
        createNewPlaylist(playlistName);
        //Wait for playlist to be created
        try {
            Thread.sleep(5000); //Added try/catch since have latest setting with java forbade sleep without throw or catch
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        successNotificationDisplayed("Create", playlistName);
        //Search for a song by song name
        searchFor(songName);
        //Ensure song displayed in search results
        verifySuccessfulSearchResults(songName);
        //Click on "View All" button
        viewAllSearchResults();
        verifySectionTitle("Showing Songs");
        //Select the song
        selectSong(songName);
        //Add song to the recently created playlist
        addSongToExistingPlaylist(playlistName);
        //Verify success notification displayed
        successNotificationDisplayed("Add", playlistName);
        //Navigate to the playlist
        openExistingPlaylist(playlistName);
        //Delete playlist for future tests
        deleteExistingPlaylist();
        //Verify success notification displayed
        successNotificationDisplayed("Delete", playlistName);
    }

    @Test
    public void addSongAndPlaylist() {
        navigateToLoginPage();
        loginIntoApplication(validEmail, validPassword);
        //Search for a song
        searchFor(songName);
        //Ensure song displayed in search results
        verifySuccessfulSearchResults(songName);
        //Click on "View All" button
        viewAllSearchResults();
        verifySectionTitle("Showing Songs");
        //Select song to add to playlist
        selectSong(songName);
        addSongToNewPlaylist(playlistName);
        //Verify success notification displayed
        successNotificationDisplayed("Create", playlistName);
        verifySectionTitle(playlistName);
        //Delete the playlist
        deleteExistingPlaylist();
        //Verify success notification displayed
        successNotificationDisplayed("Delete", playlistName);
    }
}
