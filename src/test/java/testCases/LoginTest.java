package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;

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

        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }

    @Test
    public void verifyLoginWithValidUserAndInvalidPassword() {
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getUsername());
        loginPage.enterPassword(readConfigUtils.getInvalidPassword());
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }

    @Test
    public void verifyLoginWithInvalidUserAndInvalidPassword() {
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getInvalidUsername());
        loginPage.enterPassword(readConfigUtils.getInvalidPassword());
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }
}