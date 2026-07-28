package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SortPage {

    WebDriver driver;

    // Locators
    By sortDropdown = By.xpath("//select[@data-test='product-sort-container']");
    By itemNames = By.className("inventory_item_name");

    public SortPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get all options text from the sorting dropdown
    public List<String> getSortDropdownOptionsText() {
        Select select = new Select(driver.findElement(sortDropdown));
        List<WebElement> options = select.getOptions();
        List<String> optionTexts = new ArrayList<>();

        for (WebElement option : options) {
            optionTexts.add(option.getText());
        }
        return optionTexts;
    }

    // Select a sort option by visible text
    public void selectSortOption(String visibleText) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(visibleText);
    }

    // Get product names currently displayed on the UI
    public List<String> getProductNames() {
        List<WebElement> nameElements = driver.findElements(itemNames);
        List<String> names = new ArrayList<>();

        for (WebElement element : nameElements) {
            names.add(element.getText());
        }
        return names;
    }
}