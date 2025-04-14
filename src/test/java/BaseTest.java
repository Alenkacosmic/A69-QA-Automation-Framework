import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.Locale;

public class BaseTest {
    //Global variables
    public WebDriver driver = null;
    public WebDriverWait wait = null;
    public String loginUrl = "https://qa.koel.app/";
    public String validEmail = "elena.ioksha@testpro.io"; //added actual users login since we are training, otherwise never ever add actual credentials
    public String validPassword = "e1XeRcG9"; //added actual user password since we are training, otherwise never ever add actual credentials
    public String userName = "Elena Ioksha";
    public static Actions actions = null;

    //Login page group
    public void navigateToLoginPage() {
        driver.get(loginUrl);
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
    }

    //Generators
    public String generateRandomPlaylistName() {
        Faker faker = new Faker(new Locale("en-US"));
        String newPlaylistName = faker.color().name();
        return newPlaylistName;
    }
    public String generateRandomUserName() {
        Faker faker = new Faker(new Locale("en-US"));
        String newUserName = faker.name().firstName();
        return newUserName;
    }

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameters({"BaseUrl"})
    @BeforeMethod
    public void launchBrowserAndOpenLoginPage(String BaseUrl) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        loginUrl = BaseUrl;
        navigateToLoginPage();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
