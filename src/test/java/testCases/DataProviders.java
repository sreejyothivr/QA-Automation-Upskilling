package testCases;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataProviders {

    @DataProvider(  name = "Products")
    public Object[][] Products() {

        List<String> products = new ArrayList<>(List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        ));

        Collections.shuffle(products);

        return new Object[][]{
                {products.get(0)},
                {products.get(1)},
                {products.get(2)}
        };
    }
}