package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;

import java.util.Arrays;
import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void verifyProductsRemainInCart() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.userLogin(
                readConfigUtils.getUsername(),
                readConfigUtils.getPassword()
        );

        ProductPage productPage = new ProductPage(driver);

        // Products to add
        List<String> products = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light"
        );

        // Add products to cart
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.addProductToCart("Sauce Labs Bike Light");

        // Go to Cart
        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);

        // Get products from cart
        List<String> productsBeforeNavigation =
                cartPage.getProductNames();

        // Verify products are in cart
        Assert.assertEquals(
                productsBeforeNavigation,
                products,
                "Products are not correctly displayed in the cart"
        );

        // Go back to Products page
        cartPage.clickContinueShopping();

        // Go to Cart again
        productPage.clickCart();

        // Get products from cart again
        List<String> productsAfterNavigation =
                cartPage.getProductNames();

        // Verify products remain in cart
        Assert.assertEquals(
                productsAfterNavigation,
                products,
                "Products did not remain in the cart after navigating back to Products page"
        );
    }
    @Test
    public void verifyCartBadgeCount() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.userLogin(
                readConfigUtils.getUsername(),
                readConfigUtils.getPassword()
        );

        ProductPage productPage = new ProductPage(driver);

        // Add 3 products
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.addProductToCart("Sauce Labs Bike Light");
        productPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        // Get cart badge count
        int cartBadgeCount = productPage.getCartBadgeCount();

        // Open Cart
        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);

        // Get number of products displayed in Cart
        int productsInCartCount = cartPage.getProductNames().size();

        // Verify badge count matches cart products count
        Assert.assertEquals(
                cartBadgeCount,
                productsInCartCount,
                "Cart badge count does not match the number of products in the cart"
        );
    }
    @Test
    public void verifyNavigationToProductDetailsPage() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.userLogin(
                readConfigUtils.getUsername(),
                readConfigUtils.getPassword()
        );

        ProductPage productPage = new ProductPage(driver);

        // Add product to cart
        productPage.addProductToCart("Sauce Labs Backpack");

        // Go to Cart
        productPage.clickCart();

        CartPage cartPage = new CartPage(driver);

        // Click product name in Cart
        cartPage.clickProduct("Sauce Labs Backpack");

        // Verify Product Details page
        String productDetailName = productPage.getProductDetailName();

        Assert.assertEquals(
                productDetailName,
                "Sauce Labs Backpack",
                "Clicking the product name did not navigate to the correct Product Details page"
        );
    }
    @Test
    public void verifyRemoveFromCart() {

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // Login
        loginPage.userLogin(
                readConfigUtils.getUsername(),
                readConfigUtils.getPassword()
        );

        // Product
        String productName = "Sauce Labs Backpack";

        // Add product to cart
        productPage.addProductToCart(productName);

        // Verify product is added
        Assert.assertEquals(
                productPage.getCartBadgeCount(),
                1,
                "Product was not added to cart"
        );

        // Open cart
        productPage.clickCart();

        // Remove product
        productPage.removeProductFromCart(productName);

        // Verify cart is empty
        Assert.assertFalse(
                productPage.isCartBadgeDisplayed(),
                "Cart should be empty after removing the product"
        );
    }
}