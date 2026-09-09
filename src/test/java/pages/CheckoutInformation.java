package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutInformation {

    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By cancelButton = By.id("cancel");
    private By continueButton = By.id("continue");
    private By errorMessage = By.className("error-message-container");

    public CheckoutInformation(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isFirstNameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName)
        ).isDisplayed();
    }

    public boolean isLastNameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastName)
        ).isDisplayed();
    }

    public boolean isPostalCodeDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCode)
        ).isDisplayed();
    }

    public boolean isCancelButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cancelButton)
        ).isDisplayed();
    }

    public boolean isContinueButtonDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(continueButton)
        ).isDisplayed();
    }

    public void enterFirstName(String firstNameValue) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName)
        ).sendKeys(firstNameValue);
    }

    public void enterLastName(String lastNameValue) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastName)
        ).sendKeys(lastNameValue);
    }

    public void enterPostalCode(String postalCodeValue) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCode)
        ).sendKeys(postalCodeValue);
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText().trim();
    }

    public void clickContinue() {
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        );

        continueBtn.click();
    }
}
