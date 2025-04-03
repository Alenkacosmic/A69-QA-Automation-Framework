import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.UUID;

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
    public void fillInEmail(String userEmailAddress) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys(userEmailAddress);
    }
    public void fillInPassword(String userPassword) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(userPassword);
    }
    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
    }
    public void loginIntoApplication(String userEmailAddress, String userPassword) {
        fillInEmail(userEmailAddress);
        fillInPassword(userPassword);
        submitLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='avatar']")));
    }

    //Profile settings and editing group
    public void openProfileSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='view-profile']"))).click();
        verifySectionTitle("Profile & Preferences");
    }
    public void enterCurrentPassword(String currentPassword) {
        WebElement currentPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputProfileCurrentPassword")));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }
    public void editProfileName(String newProfileName) {
        WebElement profileNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputProfileName")));
        //Clear field using keyboard since using method works unstable in current field
        profileNameField.sendKeys(Keys.CONTROL + "a");
        profileNameField.sendKeys(Keys.DELETE);
        profileNameField.sendKeys(newProfileName);
    }
    public void saveProfile() {
        WebElement saveProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-submit")));
        saveProfileButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Profile updated.')]")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Profile updated.')]")));
    }

    //Playlist composites
    public void createNewPlaylist(String playlistName) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#playlists i"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-testid='playlist-context-menu-create-simple']"))).click();
        WebElement playlistNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//input")));
        playlistNameField.sendKeys(playlistName);
        playlistNameField.sendKeys(Keys.ENTER);
        successNotificationDisplayed("Create", playlistName);
    }
    public void renamePlaylistWithDoubleClick(String playlistName, String newPlaylistName) {
        WebElement playlistLink = driver.findElement(By.xpath("//section[@id='playlists']//a[contains(text(),'" + playlistName + "')]"));
        actions.doubleClick(playlistLink).perform();
        WebElement playlistNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//a[contains(text(),'" + playlistName + "')]/following-sibling::input")));
        actions.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(newPlaylistName)
                .sendKeys(Keys.ENTER)
                .perform();
        successNotificationDisplayed("Update", newPlaylistName);
    }
    public void renamePlaylistUsingMenu(String playlistName, String newPlaylistName) {
        WebElement playlistLink = driver.findElement(By.xpath("//section[@id='playlists']//a[contains(text(),'" + playlistName + "')]"));
        actions.contextClick(playlistLink).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='menu playlist-item-menu']//li[text()='Edit']"))).click();
        WebElement playlistNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//a[contains(text(),'" + playlistName + "')]/following-sibling::input")));
        playlistNameField.sendKeys(Keys.CONTROL + "a");
        playlistNameField.sendKeys(Keys.DELETE);
        playlistNameField.sendKeys(newPlaylistName);
        playlistNameField.sendKeys(Keys.ENTER);
        successNotificationDisplayed("Update", newPlaylistName);
    }
    public void searchForSong(String searchParameter) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#searchForm>input")));
        searchField.clear();
        searchField.sendKeys(searchParameter);
        verifySectionTitle("Search Results");
    }
    public void viewAllSearchResults() {
        WebElement viewAllSearchResultsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='results']//button[contains(text(),'View All')]")));
        viewAllSearchResultsButton.click();
        verifySectionTitle("Showing Songs");
    }
    public void deleteNonEmptyPlaylist() {
        //Expects playlistType equals 'Empty' or 'NonEmpty'
        WebElement deletePlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".del.btn-delete-playlist")));
        deletePlaylistButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//p[contains(text(),'Delete the playlist')]")));
        WebElement confirmDeletion = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ok")));
        confirmDeletion.click();
    }
    public void deleteEmptyPlaylist() {
        WebElement deletePlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".del.btn-delete-playlist")));
        deletePlaylistButton.click();
    }
    public void selectSong(String songIdentifier) {
        WebElement selectedSong = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//td[text()='" + songIdentifier + "']")));
        selectedSong.click();
    }
    public void addSongToNewPlaylist(String newPlaylistName) {
        WebElement addToButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-add-to")));
        addToButton.click();
        WebElement newPlaylistNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='songResultsWrapper']//input")));
        newPlaylistNameField.sendKeys(newPlaylistName);
        WebElement submitCreateNewPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//button[@type='submit']")));
        submitCreateNewPlaylist.click();
    }
    public void addSongToExistingPlaylist(String existingPlaylistName) {
        WebElement addToButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-add-to")));
        addToButton.click();
        WebElement playlistNameOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='existing-playlists'][./*[text()='Add 1 song to']]//li[contains(text(),'" + existingPlaylistName + "')]")));
        playlistNameOption.click();
    }
    public void openExistingPlaylist(String existingPlaylistName) {
        WebElement createdPlaylistLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='playlists']//a[contains(text(),'" + existingPlaylistName + "')]")));
        createdPlaylistLink.click();
        verifySectionTitle(existingPlaylistName);
    }

    //Song controls
    public void clickNextSong() {
        //Using actions to make controls visible
        WebElement playerControls = driver.findElement(By.cssSelector(".side.player-controls"));
        actions.moveToElement(playerControls).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@data-testid='play-next-btn']"))).click();
    }
    public void clickPlaySong() {
        //Using actions to make controls visible
        WebElement playerControls = driver.findElement(By.cssSelector(".side.player-controls"));
        actions.moveToElement(playerControls).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid='play-btn']"))).click();
    }
    public boolean isSongPlaying() {
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='sound-bar-play']/img")));
        return soundBar.isDisplayed();
    }

    //Useful composites
    public String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public void successNotificationDisplayed(String action, String playlistName) {
        //Expects action equals 'Create' or 'Delete' or 'Add' or 'Update'
        switch (action) {
            case "Create" ->
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Created playlist \"" + playlistName + ".\"')]")));
            case "Delete" ->
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Deleted playlist \"" + playlistName + ".\"')]")));
            case "Add" ->
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Added 1 song into \"" + playlistName + ".\"')]")));
            case "Update" ->
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Updated playlist \"" + playlistName + ".\"')]")));
        }
    }
    public void verifySectionTitle(String sectionTitleName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + sectionTitleName + "')]")));
    }
    public void verifySuccessfulSearchResults(String searchParameter) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='songs']//span[contains(text(),'" + searchParameter + "')]")));
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
