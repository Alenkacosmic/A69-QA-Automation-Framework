import dev.failsafe.internal.util.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest {
    @Test
    public void playSong() {
        navigateToLoginPage();
        loginIntoApplication(validEmail, validPassword);
        clickNextSong();
        clickPlaySong();
        Assert.isTrue(isSongPlayng(), "Song is not playing");
    }
}
