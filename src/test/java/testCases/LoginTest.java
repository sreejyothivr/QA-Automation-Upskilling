package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;
import utils.ReadConfigUtils;

import java.util.Arrays;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getUsername());
        loginPage.enterPassword(readConfigUtils.getPassword());
        loginPage.clickLogin();
        ProductPage productPage = new ProductPage(driver);
        String title = productPage.getValuePageTitle();
        Assert.assertEquals(title, readConfigUtils.getTitle());

    }

    @Test
    public void verifyInValidLogin() {
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getInvalidUsername());
        loginPage.enterPassword(readConfigUtils.getPassword());
        loginPage.clickLogin();
        String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(errorMessage, readConfigUtils.getErrorMsg());

    }


}