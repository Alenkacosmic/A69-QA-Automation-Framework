import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    //Global variables
    public WebDriver driver = null;
    public String loginUrl = "https://qa.koel.app/";
    public String validEmail = "elena.ioksha@testpro.io"; //added actual users login since we are training, otherwise never ever add actual credentials
    public String validPassword = "e1XeRcG9"; //added actual user password since we are training, otherwise never ever add actual credentials
    public String userName = "Elena Ioksha";

    //Login page group
    public void navigateToLoginPage() {
        driver.get(loginUrl);
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
    }
    public void fillInEmail(String userEmailAddress) {
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(userEmailAddress);
    }
    public void fillInPassword(String userPassword) {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(userPassword);
    }
    public void submitLogin() {
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }
    public void loginIntoApplication(String userEmailAddress, String userPassword) {
        fillInEmail(userEmailAddress);
        fillInPassword(userPassword);
        submitLogin();
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']")); // Verify user logged in
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    //Profile settings and editing group
    public void enterCurrentPassword(String currentPassword) {
        WebElement currentPasswordField = driver.findElement(By.id("inputProfileCurrentPassword"));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }
    public void editProfileName(String newProfileName) {
        WebElement profileNameField = driver.findElement(By.id("inputProfileName"));
        profileNameField.clear();
        profileNameField.sendKeys(newProfileName);
    }
    public void saveProfile() {
        WebElement saveButton = driver.findElement(By.cssSelector(".btn-submit"));
        saveButton.click();
    }

    //Playlist composites
    public void createNewPlaylist(String playlistName) {
        WebElement addPlaylistButton = driver.findElement(By.cssSelector(".fa.fa-plus-circle.create"));
        addPlaylistButton.click();
        WebElement newPlaylistOption = driver.findElement(By.xpath("//li[text()='New Playlist']"));
        newPlaylistOption.isDisplayed();
        newPlaylistOption.click();
        WebElement playlistNameField = driver.findElement(By.xpath("//section[@id='playlists']//input"));
        playlistNameField.sendKeys(playlistName);
        playlistNameField.sendKeys(Keys.ENTER);
    }
    public void searchFor(String searchParameter) {
        WebElement searchField = driver.findElement(By.cssSelector("#searchForm>input"));
        searchField.sendKeys(searchParameter);
        verifySectionTitle("Search Results");
    }
    public void viewAllSearchResults() {
        WebElement viewAllSearchResultsButton = driver.findElement(By.xpath("//div[@class='results']//button[contains(text(),'View All')]"));
        viewAllSearchResultsButton.click();
    }
    public void deleteExistingPlaylist() {
        WebElement deletePlaylistButton = driver.findElement(By.cssSelector(".del.btn-delete-playlist"));
        deletePlaylistButton.click();
        WebElement confirmationModal = driver.findElement(By.xpath("//div//p[contains(text(),'Delete the playlist')]"));
        confirmationModal.isDisplayed();
        WebElement confirmDeletion = driver.findElement(By.cssSelector(".ok"));
        confirmDeletion.click();
    }
    public void selectSong(String songIdentifier) {
        WebElement selectedSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//td[text()='" + songIdentifier + "']"));
        selectedSong.click();
    }
    public void addSongToNewPlaylist(String newPlaylistName) {
        WebElement addToButton = driver.findElement(By.cssSelector(".btn-add-to"));
        addToButton.click();
        WebElement newPlaylistNameField = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//input"));
        newPlaylistNameField.sendKeys(newPlaylistName);
        WebElement submitCreateNewPlaylist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//button[@type='submit']"));
        submitCreateNewPlaylist.click();
    }
    public void addSongToExistingPlaylist(String existingPlaylistName) {
        WebElement addToButton = driver.findElement(By.cssSelector(".btn-add-to"));
        addToButton.click();
        //Add song to the playlist
        WebElement playlistNameOption = driver.findElement(By.xpath("//*[@class='existing-playlists'][./*[text()='Add 1 song to']]//li[contains(text(),'" + existingPlaylistName + "')]"));
        playlistNameOption.click();
    }
    public void openExistingPlaylist(String existingPlaylistName) {
        WebElement createdPlaylistLink = driver.findElement(By.xpath("//a[contains(text(),'" + existingPlaylistName + "')]"));
        createdPlaylistLink.click();
        verifySectionTitle(existingPlaylistName);
    }

    //Useful composites
    public String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public void successNotificationDisplayed(String action, String playlistName) {
        WebElement successNotification = switch (action) {
            case "Create" ->
                    driver.findElement(By.xpath("//div[@class='success show'][contains(text(),'Created playlist \"" + playlistName + ".\"')]"));
            case "Delete" ->
                    driver.findElement(By.xpath("//div[@class='success show'][contains(text(),'Deleted playlist \"" + playlistName + ".\"')]"));
            case "Add" ->
                    driver.findElement(By.xpath("//div[@class='success show'][contains(text(),'Added 1 song into \"" + playlistName + ".\"')]"));
            default -> null;
        };
        successNotification.isDisplayed();
    }
    public void verifySectionTitle(String sectionTitleName) {
        WebElement sectionTitle = driver.findElement(By.xpath("//*[contains(text(),'" + sectionTitleName + "')]"));
        Assert.assertTrue(sectionTitle.isDisplayed());
    }
    public void verifySuccessfulSearchResults(String searchParameter) {
        WebElement searchResults = driver.findElement(By.xpath("//section[@class='songs']//span[contains(text(),'" + searchParameter + "')]"));
        searchResults.isDisplayed();
    }

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void launchBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
