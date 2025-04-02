import org.testng.annotations.Test;
import org.testng.Assert;

public class Homework18 extends BaseTest {
    @Test
    public void playSong() {
        navigateToLoginPage();
        loginIntoApplication(validEmail, validPassword);
        clickNextSong();
        clickPlaySong();
        Assert.assertTrue(isSongPlaying(), "Song is not playing");
    }
}
