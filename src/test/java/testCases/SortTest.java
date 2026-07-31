package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SortPage;
import testComponents.BaseTest;

public class SortTest extends BaseTest {

    // TC_10: Verify the sorting dropdown contains all four sorting options
    @Test
    public void verifySortingDropdownContainsAllFourOptions() {
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getUsername());
        loginPage.enterPassword(readConfigUtils.getPassword());
        loginPage.clickLogin();

        SortPage sortPage = new SortPage(driver);
        List<String> actualOptions = sortPage.getSortDropdownOptionsText();

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
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getUsername());
        loginPage.enterPassword(readConfigUtils.getPassword());
        loginPage.clickLogin();

        SortPage sortPage = new SortPage(driver);
        sortPage.selectSortOption("Name (A to Z)");

        List<String> actualProductNames = sortPage.getProductNames();
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames);

        Assert.assertEquals(actualProductNames, expectedProductNames, "Products are not sorted by Name (A to Z).");
    }

    // TC_12: Verify products are sorted by Name (Z to A)
    @Test
    public void verifyProductsSortedByNameZToA() {
        driver.get(readConfigUtils.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(readConfigUtils.getUsername());
        loginPage.enterPassword(readConfigUtils.getPassword());
        loginPage.clickLogin();

        SortPage sortPage = new SortPage(driver);
        sortPage.selectSortOption("Name (Z to A)");

        List<String> actualProductNames = sortPage.getProductNames();
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());

        Assert.assertEquals(actualProductNames, expectedProductNames, "Products are not sorted by Name (Z to A).");
    }
}