package testComponents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ReadConfigUtils;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public ReadConfigUtils readConfigUtils;

    // Initialize Log4j2 Logger
    public static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setup() {
        log.info("Initializing ChromeDriver and configuring browser settings...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        readConfigUtils = new ReadConfigUtils();
        String url = readConfigUtils.getUrl();

        log.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            log.info("Closing browser session...");
            driver.quit();
            log.info("Browser session closed successfully.");
        }
    }
}