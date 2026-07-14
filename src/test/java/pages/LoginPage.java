package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By usertextBox = By.id("user-name");
    By passwordtxt = By.id("password");
    By loginButton = By.name("login-button");

    public void enterUsername(String username) {
        driver.findElement(usertextBox).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordtxt).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}

