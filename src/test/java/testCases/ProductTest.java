package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;

public class ProductTest extends BaseTest {

    @Test
    public void verifySortingField() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(),readConfigUtils.getPassword());
        ProductPage productPage = new ProductPage(driver);
        List<String> sortingOptions = productPage.getSortingOptions();
        List<String> expected = Arrays.stream(readConfigUtils.getSortingOption().split(","))
                .map(String::trim)
                .toList();

        Assert.assertEquals(sortingOptions, expected,
                "Sorting dropdown options do not match expected list.");

    }

    @Test
    public void verifySortingOptions() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(),readConfigUtils.getPassword());
        ProductPage productPage = new ProductPage(driver);
        SoftAssert softAssert = new SoftAssert();
        List<String> originalNames = productPage.getProductNames();
        List<String> expectedAscending = originalNames.stream()
                .sorted()
                .toList();
        productPage.sort("Ascending");
        List<String> actualAscending = productPage.getProductNames();
        softAssert.assertEquals(actualAscending, expectedAscending);
        List<String> expectedDescending = originalNames.stream()
                .sorted(Collections.reverseOrder())
                .toList();
        productPage.sort("Descending");
        List<String> actualDescending = productPage.getProductNames();
        softAssert.assertEquals(actualDescending, expectedDescending);
        softAssert.assertAll();
    }
    @Test
    public void verifyHamburgerNavigationMenu() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(), readConfigUtils.getPassword());

        ProductPage productPage = new ProductPage(driver);

        productPage.clickHamburgerMenu();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(
                productPage.isSideMenuDisplayed(),
                "Side Navigation Menu is not displayed."
        );

        List<String> actualOptions = productPage.getSideMenuOptions();

        List<String> expectedOptions = Arrays.stream(
                        readConfigUtils.getMenuOptions().split(","))
                .map(String::trim)
                .toList();


        softAssert.assertEquals(
                actualOptions,
                expectedOptions,
                "Side menu options are incorrect."
        );

        softAssert.assertAll();
    }
    @Test
    public void verifyAllItemsNavigationToProductsPage() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(), readConfigUtils.getPassword());

        ProductPage productPage = new ProductPage(driver);

        productPage.clickHamburgerMenu();

        productPage.clickAllItems();

        Assert.assertEquals(
                productPage.getValuePageTitle(),
                "Products",
                "Navigation to Products page failed."
        );
    }
    @Test
    public void verifyCloseButtonFunctionality() {

        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(readConfigUtils.getUsername(),readConfigUtils.getPassword());

        ProductPage productPage = new ProductPage(driver);
        productPage.clickMenuButton();

        softAssert.assertTrue(productPage.isCloseButtonDisplayed());
        productPage.clickCloseButton();

        softAssert.assertTrue(productPage.isMenuClosed());
        softAssert.assertAll();
    }
}