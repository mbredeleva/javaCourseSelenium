package fixture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseFirefoxTest {
    public FirefoxDriver driver;
    protected String url;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
