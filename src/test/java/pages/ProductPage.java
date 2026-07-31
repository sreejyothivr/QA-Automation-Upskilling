package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By pageTitle = By.className("title");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Gets the page title text (e.g., "Products")
     */
    public String getValuePageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    /**
     * Retrieves all visible options from the sorting dropdown as a list of Strings.
     */
    public List<String> getSortingOptions() {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdownElement);

        return select.getOptions().stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Selects the sorting option based on sortOrder.
     *
     * @param sortOrder "Ascending" or "Descending"
     */
    public void sort(String sortOrder) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdownElement);

        if ("Ascending".equalsIgnoreCase(sortOrder)) {
            select.selectByVisibleText("Name (A to Z)");
        }
        else if ("Descending".equalsIgnoreCase(sortOrder)) {
            select.selectByVisibleText("Name (Z to A)");
        }
        else {
            throw new IllegalArgumentException("Invalid sort order: " + sortOrder);
        }
    }

    /**
     * Helper method to fetch all product name texts currently displayed on the UI.
     */
    public List<String> getProductNames() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames))
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}