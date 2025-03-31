import org.testng.annotations.Test;

public class Homework19 extends BaseTest {
    @Test
    public void deleteEmptyPlaylist() {
        String playlistName = "EmptyPlayList";
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
    }

}
