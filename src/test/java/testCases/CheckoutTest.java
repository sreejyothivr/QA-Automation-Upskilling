package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutInformation;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;
import pages.CheckoutOverView;

public class CheckoutTest extends BaseTest {

    @Test (priority = 1)
    public void verifyAllCheckoutInformationFieldsAreDisplayed() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.userLogin(
                "standard_user",
                "secret_sauce"
        );

        ProductPage productPage = new ProductPage(driver);

        productPage.addBackpackToCart();

        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);

        // Click Checkout
        cartPage.clickCheckout();

        // Checkout page
        CheckoutInformation checkoutInformation = new CheckoutInformation(driver);

        System.out.println("First Name field is displayed: "
                + checkoutInformation.isFirstNameDisplayed());

        Assert.assertTrue(
                checkoutInformation.isFirstNameDisplayed(),
                "First Name field is not displayed"
        );

            System.out.println("Last Name field is displayed: "
                + checkoutInformation.isFirstNameDisplayed());

        Assert.assertTrue(
                checkoutInformation.isLastNameDisplayed(),
                "Last Name field is not displayed"
        );

        System.out.println("Postal Code field is displayed: "
                + checkoutInformation.isFirstNameDisplayed());

        Assert.assertTrue(
                checkoutInformation.isPostalCodeDisplayed(),
                "Zip/Postal Code field is not displayed"
        );

        System.out.println("Cancel button is displayed: "
                + checkoutInformation.isFirstNameDisplayed());

        Assert.assertTrue(
                checkoutInformation.isCancelButtonDisplayed(),
                "Cancel button is not displayed"
        );

        System.out.println("Continue button is displayed: "
                + checkoutInformation.isFirstNameDisplayed());

        Assert.assertTrue(
                checkoutInformation.isContinueButtonDisplayed(),
                "Continue button is not displayed"
        );
    }

    @Test (priority = 2)
    public void allFieldsEmpty() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutInformation checkoutInformation =
                new CheckoutInformation(driver);

        // No fields entered
        checkoutInformation.clickContinue();

        String errorMessage = checkoutInformation.getErrorMessage();

        System.out.println("Case 1 - Validation message: " + errorMessage);

        Assert.assertEquals(
                errorMessage,
                "Error: First Name is required",
                "Incorrect validation message displayed"
        );
    }

    @Test (priority = 3)
    public void lastNameEmpty() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutInformation checkoutInformation =
                new CheckoutInformation(driver);

        // Enter First Name only
        checkoutInformation.enterFirstName("Steeve");

        checkoutInformation.clickContinue();

        String errorMessage = checkoutInformation.getErrorMessage();

        System.out.println("Case 2 - Validation message: " + errorMessage);

        Assert.assertEquals(
                errorMessage,
                "Error: Last Name is required",
                "Incorrect validation message displayed"
        );
    }

    @Test (priority = 4)
    public void postalCodeEmpty() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutInformation checkoutInformation =
                new CheckoutInformation(driver);
        System.out.println("Entering First Name");
        checkoutInformation.enterFirstName("Steeve");
        System.out.println("Entering Last Name");
        checkoutInformation.enterLastName("Liz");
        System.out.println("Clicking Continue");
        checkoutInformation.clickContinue();
        System.out.println("Continue clicked");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        String errorMessage = checkoutInformation.getErrorMessage();

        System.out.println("Case 3 - Validation message: " + errorMessage);

        Assert.assertEquals(
                errorMessage,
                "Error: Postal Code is required",
                "Incorrect validation message displayed"
        );
    }

    @Test(priority = 5)
    public void CheckoutOverviewPage() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutInformation checkoutInformation =
                new CheckoutInformation(driver);

        checkoutInformation.enterFirstName("Steeve");
        checkoutInformation.enterLastName("Liz");
        checkoutInformation.enterPostalCode("691001");

        checkoutInformation.clickContinue();

        CheckoutOverView checkoutOverView =
                new CheckoutOverView(driver);

        String pageTitle = checkoutOverView.getPageTitle();

        System.out.println("Checkout page title: " + pageTitle);

        Assert.assertEquals(
                pageTitle,
                "Checkout: Overview",
                "Checkout Overview page was not displayed"
        );
    }
}