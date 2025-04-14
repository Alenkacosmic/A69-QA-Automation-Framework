package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Songs extends BasePage{
    public Songs(WebDriver givenDriver) { super(givenDriver); }

    @FindBy(css = ".btn-add-to") WebElement addToButton;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//input") WebElement newPlaylistNameField;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//button[@type='submit']") WebElement submitCreateNewPlaylist;
    @FindBy(css = "#searchForm>input") WebElement searchInputField;
    @FindBy(xpath = "//div[@class='results']//button[contains(text(),'View All')]") WebElement viewAllSearchResultsButton;

    public void searchForSong(String searchParameter) {
        searchInputField.clear();
        searchInputField.sendKeys(searchParameter);
        verifySectionTitle("Search Results");
    }

    public boolean isSongFound (String searchParameter) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='results']//*[@data-test='song-card'][.//*[contains(text(),'" + searchParameter + "')]]")))
                .isDisplayed();
    }

    public void viewAllSearchResults() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllSearchResultsButton));
        viewAllSearchResultsButton.click();
        verifySectionTitle("Showing Songs");
    }

    public void selectSong(String songIdentifier) {
        WebElement selectedSong = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//td[text()='" + songIdentifier + "']")));
        selectedSong.click();
        addToButton.isDisplayed();
    }

    public void addSongToNewPlaylist(String newPlaylistName) {
        addToButton.click();
        wait.until(ExpectedConditions.visibilityOf(newPlaylistNameField)).sendKeys(newPlaylistName);
        wait.until(ExpectedConditions.elementToBeClickable(submitCreateNewPlaylist)).click();
    }

    public void addSongToExistingPlaylist(String existingPlaylistName) {
        addToButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='existing-playlists'][./*[text()='Add 1 song to']]//li[contains(text(),'" + existingPlaylistName + "')]"))).click();
    }
}
