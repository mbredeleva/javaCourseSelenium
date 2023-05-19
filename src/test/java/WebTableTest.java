import data_structures.Employee;
import fixture.BaseFirefoxTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
        https://demoqa.com/webtables
        Change “Last name” for the user with the name “Cierra”
        Add a new row
        Delete the user with the email “alden@example.com”
*/
public class WebTableTest extends BaseFirefoxTest {

    @BeforeEach
    public void openUrl() {
        url = "https://demoqa.com/webtables";
        driver.get(url);
    }

    @Test
    public void ChangeLastNameTest() {
        String name = "Cierra";
        String newSurname = "Smith";
        List<WebElement> rows = driver.findElements(By.className("rt-tr"));
        WebElement targetRow = findRowByText(name);
        targetRow.findElement(By.cssSelector("[title=\"Edit\"]")).click();

        // Update the surname
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys(Keys.CONTROL + "a");
        lastNameField.sendKeys(newSurname);
        driver.findElement(By.id("submit")).click();

        assertThat(targetRow.getText()).contains(newSurname);
    }

    @Test
    public void addNewRow() {
        Employee employee = Employee.generateRandomEmployee();

        driver.findElement(By.id("addNewRecordButton")).click();

        WebElement modal = driver.findElement(By.className("modal-dialog"));
        assertThat(modal.isDisplayed()).isTrue();

        modal.findElement(By.id("firstName")).sendKeys(employee.getFirstName());
        modal.findElement(By.id("lastName")).sendKeys(employee.getLastName());
        modal.findElement(By.id("userEmail")).sendKeys(employee.getEmail());
        modal.findElement(By.id("age")).sendKeys(Integer.toString(employee.getAge()));
        modal.findElement(By.id("salary")).sendKeys(Integer.toString(employee.getSalary()));
        modal.findElement(By.id("department")).sendKeys(employee.getDepartment());
        modal.findElement(By.id("submit")).click();

        WebElement row = findRowByText(employee.getFirstName());
        String text = row.getText();

        assertThat(row.getText()).isEqualTo(employee.textRepresentation());
    }

    @Test
    public void deleteUser() {
        String email = "alden@example.com";

        WebElement row = findRowByText("email");
        row.findElement(By.cssSelector("[title=\"Delete\"]")).click();

        assertThat(findRowByText(email)).isNull();
    }

    WebElement findRowByText(String text) {
        List<WebElement> rows = driver.findElements(By.className("rt-tr"));
        WebElement targetRow = null;
        for (WebElement row: rows) {
            if (row.getText().contains(text)) {
                targetRow = row;
                break;
            }
        }
        return targetRow;
    }
}
