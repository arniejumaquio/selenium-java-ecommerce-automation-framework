package io.github.arniejumaquio.framework.pages;

import io.github.arniejumaquio.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By checkoutCompleteLabel = By.xpath("//span[text()='Checkout: Complete!']");
    private final By thankYouForYourOrderMsg = By.cssSelector("h2[data-test='complete-header']");
    private final By yourOrderDispatchMsg = By.cssSelector("div[data-test='complete-text']");
    private final By backToHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver){
        super(driver);
    }

    public boolean isCheckoutCompleteLabelDisplayed(){
        return isDisplayed(checkoutCompleteLabel);
    }

    public boolean isBackHomeButtonDisplayed(){
        return isDisplayed(backToHomeButton);
    }

    public String getThankYouForYourOrderMsg(){
        return getText(thankYouForYourOrderMsg);
    }

    public String getYourOrderDispatchMsg(){
        return getText(yourOrderDispatchMsg);
    }

    public ProductPage clickBackHomeButton(){

        click(backToHomeButton);

        return new ProductPage(driver);

    }




}
