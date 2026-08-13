package testComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ReadConfigUtils;

public class BaseTest {

    public  WebDriver driver;
    public ReadConfigUtils readConfigUtils;

    @BeforeMethod
    public void setup() {
       // driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.manage().window().maximize();
        readConfigUtils = new ReadConfigUtils();
        driver.get(readConfigUtils.getUrl());
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}