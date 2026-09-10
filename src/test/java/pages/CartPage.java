package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItems = By.className("inventory_item_name");
    private By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public List<String> getProductNames() {

        return wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems)
                )
                .stream()
                .map(element -> element.getText())
                .toList();
    }

    public void clickContinueShopping() {

        wait.until(
                ExpectedConditions.elementToBeClickable(continueShoppingButton)
        ).click();
    }
    public void clickProduct(String productName) {

        By product = By.xpath(
                "//div[contains(@class,'inventory_item_name') and text()='"
                        + productName + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(product)
        ).click();
    }
}