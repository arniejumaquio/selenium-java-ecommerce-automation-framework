package io.github.arniejumaquio.framework.pages;

import io.github.arniejumaquio.framework.base.BasePage;
import io.github.arniejumaquio.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public class CheckoutOverviewPage extends BasePage {

    private final By checkoutOverviewLabel = By.xpath("//span[text()='Checkout: Overview']");
    private final By checkoutItems = By.cssSelector("div.cart_item");
    private final By checkoutItemNames = By.cssSelector("div.inventory_item_name");
    private final By checkoutItemDescriptions = By.cssSelector("div.inventory_item_desc");
    private final By checkoutItemPrices = By.cssSelector("div.inventory_item_price");
    private final By paymentInformation = By.cssSelector("div[data-test='payment-info-value']");
    private final By shippingInformation = By.cssSelector("div[data-test='shipping-info-value']");
    private final By itemTotal = By.cssSelector("div[data-test='subtotal-label']");
    private final By tax = By.cssSelector("div[data-test='tax-label']");
    private final By total = By.cssSelector("div[data-test='total-label']");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
    }

    public void waitForCheckoutItemsToAppear() {
        isElementsDisplayed(checkoutItems);
    }

    public boolean waitForCheckoutItemsToDisappear() {
        return isElementsNotDisplayed(checkoutItems);
    }

    public List<WebElement>  getCheckoutItems(){
      WaitUtils.waitForElementToAppear(checkoutOverviewLabel);
      return WaitUtils.waitForElementsToAppear(checkoutItems);
    }


    public WebElement getCheckoutItem(String checkoutItemName){

     WebElement checkoutItemCard =   getCheckoutItems().stream().filter(checkoutItem -> checkoutItem.findElement(checkoutItemNames).getText().equalsIgnoreCase(checkoutItemName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Checkout item not found: "+checkoutItemName));

     return checkoutItemCard;

    }

    public List<String> getCheckoutItemNames(){
       return getCheckoutItems().stream().map(checkoutItem -> checkoutItem.findElement(checkoutItemNames).getText()).toList();
    }

    public List<String> getCheckoutItemDescriptions(){
        return getCheckoutItems().stream().map(checkoutItem -> checkoutItem.findElement(checkoutItemDescriptions).getText()).toList();
    }

    public List<String>  getCheckoutItemPrices(){
        return getCheckoutItems().stream().map(checkoutItem -> checkoutItem.findElement(checkoutItemPrices).getText()).toList();
    }

    public String getPaymentInformation(){
        return getText(paymentInformation);
    }

    public String getShippingInformation(){
        return  getText(shippingInformation);
    }

    public BigDecimal getItemTotal(){
        return new BigDecimal(removeDollarSign(getText(itemTotal)));
    }

    public BigDecimal getTax(){
        return new BigDecimal(removeDollarSign(getText(tax)));
    }

    public BigDecimal getTotal(){
        return new BigDecimal(removeDollarSign(getText(total)));
    }


    public boolean isCheckoutOverviewLabelDisplayed(){
        return isDisplayed(checkoutOverviewLabel);
    }

    public boolean isCheckoutItemNameDisplayed(String checkoutItemName){
        WebElement checkoutItemCard = getCheckoutItem(checkoutItemName);
        return isDisplayed(checkoutItemCard.findElement(checkoutItemNames));
    }

    public boolean isCheckoutItemNameNotDisplayed(String checkoutItemName){
        WebElement checkoutItemCard = getCheckoutItem(checkoutItemName);
        return isNotDisplayed(checkoutItemCard.findElement(checkoutItemNames));
    }


    public ProductPage clickCancelButton(){
        click(cancelButton);
        ProductPage productPage = new ProductPage(driver);
        productPage.waitForProductsToAppear();
        return productPage;
    }

    public CheckoutCompletePage clickFinishButton(){
        click(finishButton);

        return new CheckoutCompletePage(driver);
    }


}
