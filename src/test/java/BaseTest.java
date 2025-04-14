import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.github.javafaker.Faker;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BaseTest {
    //Global variables
    public WebDriver driver = null;
    public WebDriverWait wait = null;
    public String loginUrl = "https://qa.koel.app/";
    public String validEmail = "elena.ioksha@testpro.io"; //added actual users login since we are training, otherwise never ever add actual credentials
    public String validPassword = "e1XeRcG9"; //added actual user password since we are training, otherwise never ever add actual credentials
    public String userName = "Elena Ioksha";
    public static Actions actions = null;

    private WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String gridURL = "http://192.168.12.151:4444";
        switch(browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                firefoxOptions.addPreference("dom.popup_maximum", 0);
                firefoxOptions.addPreference("dom.disable_open_during_load", true);
                return driver = new FirefoxDriver(firefoxOptions);
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
            case "chrome":
                WebDriverManager.chromiumdriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                return driver = new ChromeDriver(options);
            case "grid-firefox":
                capabilities.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            case "grid-safari":
                capabilities.setCapability("browserName", "safari");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            case "grid-chrome":
                capabilities.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            default:
                // Default to local Chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions defaultOptions = new ChromeOptions();
                defaultOptions.addArguments("--remote-allow-origins=*");
                defaultOptions.addArguments("--disable-notifications");
                return driver = new ChromeDriver(defaultOptions);
        }
    }

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

        //WebDriverManager.chromedriver().setup();
    }

    @Parameters({"BaseUrl"})
    @BeforeMethod
    public void launchBrowserAndOpenLoginPage(String BaseUrl) throws MalformedURLException {
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--disable-notifications");

        driver = pickBrowser(System.getProperty("browser"));
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
