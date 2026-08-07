package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
 import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("title");
    private By sortDropdown = By.className("product_sort_container");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By hamburgerMenu = By.id("react-burger-menu-btn");
    private By sideMenu = By.className("bm-menu-wrap");
    private By menuOptions = By.cssSelector(".bm-item.menu-item");
    private By allItems = By.id("inventory_sidebar_link");
    //private By menuButton = By.id("react-burger-menu-btn");
    private By closeButton = By.id("react-burger-cross-btn");
    private By logoutButton = By.id("logout_sidebar_link");
    private By about = By.id("about_sidebar_link");
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
    public void clickHamburgerMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu)).click();
    }
    public boolean isSideMenuDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sideMenu))
                .isDisplayed();
    }

    public List<String> getSideMenuOptions() {

        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menuOptions))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(allItems)).click();
    }

    public void clickMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu)).click();
    }
    public boolean isCloseButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton)).isDisplayed();
    }
    public void clickCloseButton() {
        WebElement close =
                wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        close.click();
    }
    public boolean isMenuClosed() {
        return wait.until(ExpectedConditions.attributeToBe(sideMenu, "aria-hidden", "true"));
    }
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
    public void clickAbout() {

        WebElement aboutLink =
                wait.until(ExpectedConditions.visibilityOfElementLocated(about));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", aboutLink);
    }

    public String getAboutCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getAboutPageTitle() {
        return driver.getTitle();
    }
    public void waitForAboutPage() {

        wait.until(ExpectedConditions.urlContains("saucelabs.com"));

    }
}