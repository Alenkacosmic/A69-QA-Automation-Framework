package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Playlists extends BasePage{
    public Playlists(WebDriver givenDriver) { super(givenDriver); }

    @FindBy(css = "#playlists i") WebElement addPlaylistButton;
    @FindBy(xpath = "//li[@data-testid='playlist-context-menu-create-simple']") WebElement addSimplePlaylistOption;
    @FindBy(xpath = "//section[@id='playlists']//input") WebElement playlistNameField;
    @FindBy(css = ".del.btn-delete-playlist") WebElement deletePlaylistButton;
    @FindBy(xpath = "//div//p[contains(text(),'Delete the playlist')]") WebElement deletePlaylistModal;
    @FindBy(css = ".ok") WebElement confirmDeletionButton;

    public void createNewPlaylist(String playlistName) {
        addPlaylistButton.isDisplayed();
        addPlaylistButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(addSimplePlaylistOption)).click();
        wait.until(ExpectedConditions.visibilityOf(playlistNameField));
        playlistNameField.sendKeys(playlistName);
        playlistNameField.sendKeys(Keys.ENTER);
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
    }

    public boolean successNotificationIsDisplayed(String action, String playlistName) {
        //Expects action equals 'Create' or 'Delete' or 'Add' or 'Update'
        switch (action) {
            case "Create" -> {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Created playlist \"" + playlistName + ".\"')]")))
                        .isDisplayed();
            }
            case "Delete" -> {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Deleted playlist \"" + playlistName + ".\"')]")))
                        .isDisplayed();
            }
            case "Add" -> {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Added 1 song into \"" + playlistName + ".\"')]")))
                        .isDisplayed();
            }
            case "Update" -> {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show'][contains(text(),'Updated playlist \"" + playlistName + ".\"')]")))
                        .isDisplayed();
            }
        }
        return false;
    }

    public void openExistingPlaylist(String existingPlaylistName) {
        WebElement playlistLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='playlists']//a[contains(text(),'" + existingPlaylistName + "')]")));
        playlistLink.click();
        verifySectionTitle(existingPlaylistName);
    }

    public void verifyPlaylistTitle(String playlistTitleName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistTitleName + "')]")));
    }

    public void deleteNonEmptyPlaylist() {
        deletePlaylistButton.isDisplayed();
        deletePlaylistButton.click();
        wait.until(ExpectedConditions.visibilityOf(deletePlaylistModal));
        confirmDeletionButton.isDisplayed();
        confirmDeletionButton.click();
    }

    public void deleteEmptyPlaylist() {
        deletePlaylistButton.isDisplayed();
        deletePlaylistButton.click();
    }
}
