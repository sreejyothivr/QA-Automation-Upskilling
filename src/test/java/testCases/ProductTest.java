package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import testComponents.BaseTest;

public class ProductTest extends BaseTest {

    // Helper method to reusable login logic for product page tests
    private ProductPage loginAndNavigateToProductPage() {
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getUsername());
        loginPage.enterPassword(readConfigUtils.getPassword());
        loginPage.clickLogin();
        return new ProductPage(driver);
    }

    // TC_10: Verify the sorting dropdown contains all four sorting options
    @Test
    public void verifySortingDropdownContainsAllFourOptions() {
        ProductPage productPage = loginAndNavigateToProductPage();

        List<String> actualOptions = productPage.getSortDropdownOptionsText();
        List<String> expectedOptions = Arrays.asList(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)"
        );

        Assert.assertEquals(actualOptions.size(), 4, "Dropdown options count mismatch.");
        Assert.assertEquals(actualOptions, expectedOptions, "Dropdown option text content mismatch.");
    }

    // TC_11: Verify products are sorted by Name (A to Z)
    @Test
    public void verifyProductsSortedByNameAToZ() {
        ProductPage productPage = loginAndNavigateToProductPage();

        productPage.selectSortOption("Name (A to Z)");

        List<String> actualProductNames = productPage.getProductNames();
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames);

        Assert.assertEquals(actualProductNames, expectedProductNames, "Products are not sorted by Name (A to Z).");
    }

    // TC_12: Verify products are sorted by Name (Z to A)
    @Test
    public void verifyProductsSortedByNameZToA() {
        ProductPage productPage = loginAndNavigateToProductPage();

        productPage.selectSortOption("Name (Z to A)");

        List<String> actualProductNames = productPage.getProductNames();
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());

        Assert.assertEquals(actualProductNames, expectedProductNames, "Products are not sorted by Name (Z to A).");
    }
}