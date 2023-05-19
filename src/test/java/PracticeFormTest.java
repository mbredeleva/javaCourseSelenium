import fixture.BaseFirefoxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*Fill in all necessary fields, and submit the form.
Fill in all necessary fields + a few additional fields, and submit the form*/
public class PracticeFormTest extends BaseFirefoxTest {

    private static final String successMessage = "Thanks for submitting the form";

    @BeforeEach
    public void openUrl() {
        url = "https://demoqa.com/automation-practice-form";
        driver.get(url);
    }

    @Test
    @DisplayName("Fill in all necessary fields, and submit the form")
    public void submitFormWithNecessaryFields() {
        driver.findElement(By.id("firstName")).sendKeys("name");
        driver.findElement(By.id("lastName")).sendKeys("surname");
        driver.findElement(By.className("custom-radio")).click(); // click first element in radio
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        WebElement button = driver.findElement(By.id("submit"));
        driver.executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
        String modalHeader = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
        assertThat(modalHeader).isEqualTo(successMessage);
    }

    @Test
    @DisplayName("Fill in all necessary fields, + a few additional fields, and submit the form")
    public void submitFormWithMoreFields() {
        Actions actions = new Actions(driver);
        driver.findElement(By.id("firstName")).sendKeys("name");
        driver.findElement(By.id("lastName")).sendKeys("surname");

        driver.findElement(By.id("userEmail")).sendKeys("my.email@example.com");
        WebElement calendarInput = driver.findElement(By.id("dateOfBirthInput"));
        calendarInput.click();
        calendarInput.sendKeys(Keys.CONTROL + "a");
        calendarInput.sendKeys("11 January 1999");

        List<WebElement> checkboxes =  driver.findElements(By.className("custom-checkbox"));//custom-checkbox
        for (WebElement checkbox: checkboxes) {
            checkbox.click();
        }

        driver.findElement(By.className("custom-radio")).click(); // click first element in radio
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        WebElement button = driver.findElement(By.id("submit"));
        driver.executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
        String modalHeader = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
        assertThat(modalHeader).isEqualTo(successMessage);

    }
}
