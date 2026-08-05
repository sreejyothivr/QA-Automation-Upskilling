package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Locators
    By usertextBox = By.id("user-name");
    By passwordtxt = By.id("password");
    By loginButton = By.name("login-button");
    By errorMessageContainer = By.xpath("//div[contains(@class, 'error-message-container')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usertextBox).sendKeys(username);
    }

    public void userLogin(String userName,String passWord)
    {
        enterUsername(userName);
        enterPassword(passWord);
        clickLogin();
    }



    public void enterPassword(String password) {
        driver.findElement(passwordtxt).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageContainer).getText();
    }

    public String getUsernamePlaceholder() {return driver.findElement(usertextBox).getAttribute("placeholder");}

    public String getPasswordPlaceholder() {return driver.findElement(passwordtxt).getAttribute("placeholder");}
}