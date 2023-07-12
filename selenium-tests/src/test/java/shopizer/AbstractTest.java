package shopizer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.time.Duration;

public class AbstractTest {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @BeforeEach
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.102.195:8080/shopizer/shop");

        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


    @AfterEach
    public void teardown() {
        driver.close();
    }

}
