package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;

    // Locators
    private By usertextBox = By.id("user-name");
    private By passwordtxt = By.id("password");
    private By loginButton = By.name("login-button");
    private By errorMessageContainer = By.xpath("//div[contains(@class, 'error-message-container')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        log.info("Entering username: {}", username);
        driver.findElement(usertextBox).sendKeys(username);
    }

    public void enterPassword(String password) {
        log.info("Entering password");
        driver.findElement(passwordtxt).sendKeys(password);
    }

    public void clickLogin() {
        log.info("Clicking login button");
        driver.findElement(loginButton).click();
    }

    public void userLogin(String userName, String passWord) {
        log.info("Performing user login sequence for user: {}", userName);
        enterUsername(userName);
        enterPassword(passWord);
        clickLogin();
    }

    public String getErrorMessage() {
        String errorMsg = driver.findElement(errorMessageContainer).getText();
        log.info("Retrieved error message: {}", errorMsg);
        return errorMsg;
    }

    public String getUsernamePlaceholder() {
        String placeholder = driver.findElement(usertextBox).getAttribute("placeholder");
        log.info("Username placeholder value: {}", placeholder);
        return placeholder;
    }

    public String getPasswordPlaceholder() {
        String placeholder = driver.findElement(passwordtxt).getAttribute("placeholder");
        log.info("Password placeholder value: {}", placeholder);
        return placeholder;
    }
}