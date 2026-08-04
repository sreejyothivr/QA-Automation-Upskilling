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

    private By pageTitle = By.className("title");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getValuePageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public List<String> getSortingOptions() {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdownElement);

        return select.getOptions().stream()
                .map(WebElement::getText)
                .toList();
    }

    public void sort(String sortOrder) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdownElement);

        if ("Ascending".equalsIgnoreCase(sortOrder)) {
            select.selectByVisibleText("Name (A to Z)");
        }
        else if ("Descending".equalsIgnoreCase(sortOrder)) {
            select.selectByVisibleText("Name (Z to A)");
        }
        else if ("Price Low to High".equalsIgnoreCase(sortOrder)){
            select.selectByVisibleText("Price (low to high)");
        }
        else if ("Price High to Low".equalsIgnoreCase(sortOrder)){
            select.selectByVisibleText("Price (high to low)");
        }
        else {
            throw new IllegalArgumentException("Invalid sort order: " + sortOrder);
        }
    }
    public List<String> getProductNames() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames))
                .stream()
                .map(WebElement::getText)
                .toList();
    }
    public List<Double> getProductPrices(){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices))
                .stream()
                .map(WebElement::getText)
                .map(price -> price.replaceAll("[^0-9.]", ""))
                .map(Double::parseDouble)
                .toList();

    }
}