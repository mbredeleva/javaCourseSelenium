import fixture.BaseFirefoxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//Select all from the “Downloads” folder
//Select “Angular”
public class CheckboxesTest extends BaseFirefoxTest {

    @BeforeEach
    public void openUrl() {
        url = "https://demoqa.com/checkbox";
        driver.get(url);
    }

    @Test
    public void selectCheckboxes() throws InterruptedException {
        driver.findElement(By.cssSelector("button.rct-option-expand-all")).click();

        clickCheckboxWithText("Angular");
        clickCheckboxWithText("Downloads");
        String resultText = driver.findElement(By.id("result")).getText();
        assertThat(resultText).contains("angular").contains("downloads").contains("wordFile").contains("excelFile");
        System.out.println();
    }

    private void clickCheckboxWithText(String text) {
        String xpathString = "//label[.//span[text()=\"" + text + "\"]]";
        WebElement checkboxParent = driver.findElement(By.xpath(xpathString));
        driver.executeScript("arguments[0].scrollIntoView(true);", checkboxParent);
        checkboxParent.findElement(By.className("rct-checkbox")).click();
    }
}
