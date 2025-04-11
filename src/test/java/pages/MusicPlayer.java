package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MusicPlayer extends BasePage{
    public MusicPlayer(WebDriver givenDriver) { super(givenDriver); }

    @FindBy(css = ".side.player-controls") WebElement playerControls;
    @FindBy(xpath = "//i[@data-testid='play-next-btn']") WebElement playNextButton;
    @FindBy(xpath = "//span[@data-testid='play-btn']") WebElement playSongButton;
    @FindBy(xpath = "//div[@data-testid='sound-bar-play']/img") WebElement soundBar;

    public void clickNextSong() {
        actions.moveToElement(playerControls).perform();
        wait.until(ExpectedConditions.elementToBeClickable(playNextButton)).click();
    }
    public void clickPlaySong() {
        actions.moveToElement(playerControls).perform();
        wait.until(ExpectedConditions.elementToBeClickable(playSongButton)).click();
    }
    public boolean isSongPlaying() {
        wait.until(ExpectedConditions.visibilityOf(soundBar));
        return soundBar.isDisplayed();
    }
}
