import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    String invalidEmail = "vasyaPupkin@testpro.gogo";

    @Test
    public void loginEmptyEmailPassword() {
        navigateToLoginPage();

        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
    }

    @Test
    public void loginValidEmailValidPassword() {
        navigateToLoginPage();
        loginIntoApplication(validEmail, validPassword);
    }

    @Test
    public void loginInvalidEmailValidPassword() {
        navigateToLoginPage();
        fillInEmail("vasyaPupkin@testpro.gogo");
        fillInPassword(validPassword);
        submitLogin();

        try {
            Thread.sleep(2000); //Added try/catch since have latest setting with java forbade sleep without throw or catch
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl); //Verify user stays on login page
    }

    @Test
    public void loginValidEmailEmptyPassword() {
        navigateToLoginPage();
        fillInEmail(validEmail);
        fillInPassword("");
        submitLogin();

        try {
            Thread.sleep(2000); //Added try/catch since have latest setting with java forbade sleep without throw or catch
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl); //Verify user stays on login page
    }
}

