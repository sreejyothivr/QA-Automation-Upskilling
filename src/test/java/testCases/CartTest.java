package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;
import org.openqa.selenium.By;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void ProductQuantityIsOne() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);

        productPage.addBackpackToCart();
        System.out.println("Backpack added");
        productPage.addBikeLightToCart();
        System.out.println("Bike Light added");
        productPage.addBoltTShirtToCart();
        System.out.println("Bolt T-Shirt added");
        System.out.println(
                "Cart count: " +
                        driver.findElement(By.className("shopping_cart_badge")).getText()
        );

        productPage.clickCart();

        System.out.println("Current URL: " + driver.getCurrentUrl());

        CartPage cartPage = new CartPage(driver);
        List<WebElement> cartItems = cartPage.getCartItems();

        System.out.println("Cart items found: " + cartItems.size());

        Assert.assertEquals(
                cartItems.size(),
                3,
                "Expected 3 products in the cart."
        );

        for (WebElement cartItem : cartItems) {

            String actualQuantity =
                    cartPage.getProductQuantity(cartItem);

            System.out.println("Product quantity: " + actualQuantity);

            Assert.assertEquals(
                    actualQuantity,
                    "1",
                    "Product quantity is not 1 by default."
            );

        }
    }
}