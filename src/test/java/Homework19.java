import org.testng.annotations.Test;

public class Homework19 extends BaseTest {
    @Test
    public void deletePlaylist() {
        String playlistName = "EmptyPlayList";

        loginIntoApplication(validEmail, validPassword);
        createNewPlaylist(playlistName);
        deleteEmptyPlaylist();
        successNotificationDisplayed("Delete", playlistName);
    }

}
