package testCases;   // Change this to your actual package

import org.testng.annotations.DataProvider;



import org.testng.annotations.DataProvider;

public class ProductDataProvider {

    @DataProvider(name = "productDetails")
    public Object[][] productDetails() {

        return new Object[][]{
                {
                        "Sauce Labs Backpack",
                        "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                        "$29.99"
                },
                {
                        "Sauce Labs Bike Light",
                        "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                        "$9.99"
                },
                {
                        "Sauce Labs Bolt T-Shirt",
                        "Get your testing superhero on with the Sauce Labs Bolt T-Shirt. From American Apparel, 100% ringspun combed cotton.",
                        "$15.99"
                }
        };
    }
}