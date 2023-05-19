import fixture.BaseFirefoxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    https://demoqa.com/accordian
    Open the accordion “Where does it come from?” and check that
    text contains “Hampden-Sydney College”
 */
public class AccordionTest extends BaseFirefoxTest {
    @BeforeEach
    public void openUrl() {
        url = "https://demoqa.com/accordian";
        driver.get(url);
    }

    @Test
    public void checkTextInAccordionSection() {
        String targetHeader = "Where does it come from?";
        String targetText = "Hampden-Sydney College";
        WebElement targetSection = null;
        for (WebElement section: driver.findElements(By.className("card"))) {
            if (section.getText().contains(targetHeader)) {
                targetSection = section;
                break;
            }
        }
        assertThat(targetSection).isNotNull();
        targetSection.click();
        assertThat(targetSection.findElement(By.className("card-body")).getText()).contains(targetText);
    }
}
