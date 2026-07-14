package testComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ReadConfigUtils;

public class BaseTest {

    public  WebDriver driver;
    public ReadConfigUtils readConfigUtils;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.manage().window().maximize();
        readConfigUtils = new ReadConfigUtils();
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}