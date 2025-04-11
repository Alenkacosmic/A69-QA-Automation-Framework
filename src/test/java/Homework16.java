import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class Homework16 extends BaseTest {
    @Test
    public void registrationNavigation() {
        String registrationUrl = "https://qa.koel.app/registration";
        LoginPage loginpage = new LoginPage(driver);

        loginpage.openRegistrationPage();
        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl);
    }
}
