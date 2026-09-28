package io.github.arniejumaquio.framework.pages;

import io.github.arniejumaquio.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    private final By checkoutYourInformationLabel = By.xpath("//span[text()='Checkout: Your Information']");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipPostalCodeField = By.id("postal-code");
    private final By cancelButton = By.id("cancel");
    private final By continueButton = By.id("continue");

    public CheckoutInformationPage(WebDriver driver){
        super(driver);
    }


    public void fillUpCheckoutInfo(String firstName,String lastName,String zipPostalCode){
        type(firstNameField,firstName);
        type(lastNameField,lastName);
        type(zipPostalCodeField,zipPostalCode);
    }

    public boolean isCheckoutYourInformationLabelDisplayed(){
        return isDisplayed(checkoutYourInformationLabel);
    }

    public CartPage clickCancelButton(){
        click(cancelButton);
        return new CartPage(driver);
    }

    public CheckoutOverviewPage clickContinueButton(){
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }




}
