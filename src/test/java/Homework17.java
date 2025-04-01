import org.testng.annotations.Test;

public class Homework17 extends BaseTest {
    String songName = "BornKing";
    String playlistName = "TestPlaylist";


    @Test
    public void addSongToPlaylist() {
        loginIntoApplication(validEmail, validPassword);
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
        loginIntoApplication(validEmail, validPassword);
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
