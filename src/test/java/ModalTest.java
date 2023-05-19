import fixture.BaseFirefoxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

/*
    https://demoqa.com/modal-dialogs
    Check text in modals.
 */
public class ModalTest extends BaseFirefoxTest {

    private final String smallModalHeader = "Small Modal";
    private final String smallModalText = "This is a small modal. It has very less content";
    private final String largeModalHeader = "Large Modal";
    private final String largeModalText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took " +
            "a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, " +
            "but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised " +
            "in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with " +
            "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

    @BeforeEach
    public void openUrl() {
        url = "https://demoqa.com/modal-dialogs";
        driver.get(url);
    }

    @Test
    public void smallModalTest() {
        driver.findElement(By.id("showSmallModal")).click();

        WebElement smallModal = driver.findElement(By.className("modal-sm"));
        assertThat(smallModal.isDisplayed()).isTrue();
        assertThat(smallModal.findElement(By.className("modal-header")).getText()).startsWith(smallModalHeader);
        assertThat(smallModal.findElement(By.className("modal-body")).getText()).isEqualTo(smallModalText);
    }

    @Test
    public void largeModalTest() {
        driver.findElement(By.id("showLargeModal")).click();

        WebElement largeModal = driver.findElement(By.className("modal-lg"));
        assertThat(largeModal.isDisplayed()).isTrue();
        assertThat(largeModal.findElement(By.className("modal-header")).getText()).startsWith(largeModalHeader);
        assertThat(largeModal.findElement(By.className("modal-body")).getText()).isEqualTo(largeModalText);
    }



}
