import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.loginIntoApplication(validEmail, validPassword);
        Assert.assertTrue(homePage.isAvatarDisplayed());
        homePage.logout();
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class)
    public void loginInvalidCredentials(String invalidEmail, String invalidPassword) {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.loginIntoApplication(invalidEmail, invalidPassword);
        Assert.assertTrue(loginPage.isSubmitButtonDisplayed());
    }
}

