package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class ReadConfigUtils {

    Properties pro;

    public ReadConfigUtils() {

        File src = new File(System.getProperty("user.dir") + "/src/main/resources/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();// properties added
            pro.load(fis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getUrl() {
        return pro.getProperty("url");
    }

    public String getUsername() {
        return pro.getProperty("username");
    }

    public String getPassword() {
        return pro.getProperty("password");
    }

    public String getTitle() {
        return pro.getProperty("productPageTitle");
    }

    public String getSortingOption() {
        return pro.getProperty("sortingOptions");
    }

    public String readProduct1() {
        return pro.getProperty("product1");
    }

    public List<String> readProduct1Details() {
        return List.of(
                pro.getProperty("product1"),
                pro.getProperty("product1Description"),
                pro.getProperty("product1Prize")
        );
    }

    public String getInvalidUsername() {
        return pro.getProperty("inValidusername");
    }

    public String getErrorMsg() {
        return pro.getProperty("errorMsg");
    }
    public String getFirstName() {
        return pro.getProperty("firstName");
    }
    public String getLastName() {
        return pro.getProperty("lastName");
    }
    public String getPostalCode() {
        return pro.getProperty("zipCode");
    }

    public String getFistNameMandatoryErrorMsg() {
        return pro.getProperty("fnameErrorMsg");
    }

    public String getLastNameMandatoryErrorMsg() {
        return pro.getProperty("lnameErrorMsg");
    }

    public String getPostalCodeMandatoryErrorMsg() {
        return pro.getProperty("postalCodeErrorMsg");
    }

    public String getCheckoutOverviewTitle() {
        return pro.getProperty("checkoutOverviewTitle");
    }

}







