package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;
import org.testng.asserts.SoftAssert;


public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(),readConfigUtils.getPassword());
        ProductPage productPage = new ProductPage(driver);
        String title = productPage.getValuePageTitle();
        Assert.assertEquals(title, readConfigUtils.getTitle());
    }

    @Test
    public void verifyInValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getInvalidUsername(),readConfigUtils.getPassword());
        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }

    @Test
    public void verifyLoginWithValidUserAndInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(),readConfigUtils.getInvalidPassword());
        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }

    @Test
    public void verifyLoginWithInvalidUserAndInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getInvalidUsername(),readConfigUtils.getInvalidPassword());
        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }
    @Test
    public void verifyLoginWithEmptyUsernameAndPassword()
    {
        driver.get(readConfigUtils.getUrl());

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getEmptyCredentialsError());
    }

    @Test
    public void verifyUsernameAndPasswordPlaceholders() {

        SoftAssert softAssert = new SoftAssert();

        driver.get(readConfigUtils.getUrl());

        LoginPage loginPage = new LoginPage(driver);

        softAssert.assertEquals(loginPage.getUsernamePlaceholder(), readConfigUtils.getUsernamePlaceholder(), "Username placeholder mismatch");

        softAssert.assertEquals(loginPage.getPasswordPlaceholder(), readConfigUtils.getPasswordPlaceholder(), "Password placeholder mismatch");
        softAssert.assertAll();
    }
}