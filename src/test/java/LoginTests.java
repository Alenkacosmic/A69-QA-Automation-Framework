import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidCredentials() {
        loginIntoApplication(validEmail, validPassword);
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class)
    public void loginInvalidCredentials(String invalidEmail, String invalidPassword) {
        fillInEmail(invalidEmail);
        fillInPassword(invalidPassword);
        submitLogin();
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl); //Verify user stays on login page
    }
}

