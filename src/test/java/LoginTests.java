import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class)
    public void loginInvalidCredentials(String invalidEmail, String invalidPassword) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoApplication(invalidEmail, invalidPassword);
        Assert.assertTrue(loginPage.isSubmitButtonDisplayed()); //Verify user stays on login page
    }
}

