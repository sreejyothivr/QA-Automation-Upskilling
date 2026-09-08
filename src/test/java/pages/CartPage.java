package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By cartItems = By.className("cart_item");
    private By cartQuantity = By.className("cart_quantity");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<WebElement> getCartItems() {

        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems)
        );
    }

    public String getProductQuantity(WebElement cartItem) {

        return cartItem.findElement(cartQuantity).getText();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}