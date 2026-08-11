package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        log.info("Starting test: verifyValidLogin");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(), readConfigUtils.getPassword());

        ProductPage productPage = new ProductPage(driver);
        String title = productPage.getValuePageTitle();

        log.info("Validating page title after successful login");
        Assert.assertEquals(title, readConfigUtils.getTitle());
    }

    @Test
    public void verifyInValidLogin() {
        log.info("Starting test: verifyInValidLogin");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getInvalidUsername(), readConfigUtils.getPassword());

        log.info("Validating error message for invalid username login attempt");
        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }

    @Test
    public void verifyLoginWithValidUserAndInvalidPassword() {
        log.info("Starting test: verifyLoginWithValidUserAndInvalidPassword");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(), readConfigUtils.getInvalidPassword());
        log.info("Validating error message for invalid password login attempt");
        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }

    @Test
    public void verifyLoginWithInvalidUserAndInvalidPassword() {
        log.info("Starting test: verifyLoginWithInvalidUserAndInvalidPassword");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getInvalidUsername(), readConfigUtils.getInvalidPassword());

        log.info("Validating error message for invalid username & password combination");
        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getErrorMsgIncorrectCredentials());
    }

    @Test
    public void verifyLoginWithEmptyUsernameAndPassword() {
        log.info("Starting test: verifyLoginWithEmptyUsernameAndPassword");
        driver.get(readConfigUtils.getUrl());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        log.info("Validating error message for empty credentials submission");
        Assert.assertEquals(loginPage.getErrorMessage(), readConfigUtils.getEmptyCredentialsError());
    }

    @Test
    public void verifyUsernameAndPasswordPlaceholders() {
        log.info("Starting test: verifyUsernameAndPasswordPlaceholders");
        SoftAssert softAssert = new SoftAssert();

        driver.get(readConfigUtils.getUrl());

        LoginPage loginPage = new LoginPage(driver);
        log.info("Checking username and password placeholder text");
        softAssert.assertEquals(loginPage.getUsernamePlaceholder(), readConfigUtils.getUsernamePlaceholder(), "Username placeholder mismatch");
        softAssert.assertEquals(loginPage.getPasswordPlaceholder(), readConfigUtils.getPasswordPlaceholder(), "Password placeholder mismatch");
        softAssert.assertAll();
    }

    @Test
    public void verifyLogout() {
        log.info("Starting test: verifyLogout");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(), readConfigUtils.getPassword());

        ProductPage productPage = new ProductPage(driver);
        String title = productPage.getValuePageTitle();
        Assert.assertEquals(title, readConfigUtils.getTitle());

        log.info("Clicking hamburger menu and performing logout");
        productPage.clickHamburgerMenu();
        productPage.clickLogout();

        String currentUrl = driver.getCurrentUrl();
        log.info("Validating URL after logout redirection");
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/", "User was not logged out properly");
    }
}