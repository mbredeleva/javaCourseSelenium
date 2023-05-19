import fixture.BaseFirefoxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    http://uitestingplayground.com/visibility
    Press the “Hide button” and check whether other buttons are visible
 */
public class VisibilityTest extends BaseFirefoxTest {

    @BeforeEach
    public void openUrl() {
        url = "http://uitestingplayground.com/visibility";
        driver.get(url);
    }

    @Test
    public void visibilityTest() {
        // You should shoot the developer who creates 7 buttons with different kinds of hiding
        // It is madness )))
        WebElement hideButton = driver.findElement(By.id("hideButton"));
        WebElement removedButton = driver.findElement(By.id("removedButton"));
        WebElement zeroWidthButton = driver.findElement(By.id("zeroWidthButton"));
        WebElement overlappedButton = driver.findElement(By.id("overlappedButton"));
        WebElement invisibleButton = driver.findElement(By.id("invisibleButton"));
        WebElement transparentButton = driver.findElement(By.id("transparentButton"));
        WebElement notDisplayedButton = driver.findElement(By.id("notdisplayedButton"));
        WebElement offscreenButton = driver.findElement(By.id("offscreenButton"));

        hideButton.click();

        //I'm really feel how I'm getting older )))
        try {
            driver.findElement(By.id("removedButton"));
            assertThat(false).withFailMessage("Button 'Remove' was not removed!");
        } catch (NoSuchElementException e) {
            System.out.println("It's ok");
        }

        try {
            overlappedButton.click();
            assertThat(false).withFailMessage("Button 'Overlapped' is clickable!");
        } catch (ElementClickInterceptedException e) {
            System.out.println("It's ok");
        }

        assertThat(zeroWidthButton.getAttribute("class").contains("zerowidth"));
        assertThat(offscreenButton.getAttribute("class").contains("offscreen"));

        assertThat(transparentButton.getAttribute("style").contains("opacity: 0;"));
        assertThat(invisibleButton.getAttribute("style").contains("visibility: hidden;"));
        assertThat(notDisplayedButton.getAttribute("style").contains("display: none;"));
    }
}
