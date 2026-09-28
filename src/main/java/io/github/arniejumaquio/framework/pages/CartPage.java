package io.github.arniejumaquio.framework.pages;

import io.github.arniejumaquio.framework.base.BasePage;
import io.github.arniejumaquio.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class CartPage extends BasePage {

    private final By yourCartLabel = By.xpath("//span[text()='Your Cart']");
    private final By cartItems = By.cssSelector("div.cart_item");
    private final By cartItemNames = By.cssSelector("div.inventory_item_name");
    private final By cartItemDescriptions = By.cssSelector("div.inventory_item_desc");
    private final By cartItemPrices = By.cssSelector("div.inventory_item_price");
    private final By removeToCartButtons = By.xpath(".//button[text()='Remove']");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");

    //dynamic locators
    private By cartItemName(String cartItemName) {
        return By.xpath("//div[@class='inventory_item_name' and text()='" + cartItemName + "']");
    }


    public CartPage(WebDriver driver) {
        super(driver);
        WaitUtils.waitForElementToAppear(yourCartLabel);
    }


    public void waitForCartItemsToAppear() {
        isElementsDisplayed(cartItems);
    }

    public boolean waitForCartItemsToDisAppear() {
       return isElementsNotDisplayed(cartItems);
    }

    private List<WebElement> getCartItems() {
        WaitUtils.waitForElementToAppear(yourCartLabel);
        return WaitUtils.waitForElementsToAppear(cartItems);
    }


    private WebElement getCartItem(String cartItemName) {
        return getCartItems().stream()
                .filter(cartItem -> cartItem.findElement(cartItemNames).getText().equalsIgnoreCase(cartItemName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cart item not found " + cartItemName));
    }

    public void removeItemToCart(String cartItemToRemove) {
        WebElement cartItem = getCartItem(cartItemToRemove);
        click(cartItem.findElement(removeToCartButtons));
    }

    public void removeItemToCart(String cartItemToRemove,int numOfClick) {
        WebElement cartItem = getCartItem(cartItemToRemove);
        multipleClick(cartItem.findElement(removeToCartButtons),numOfClick);
    }


    public void removeItemToCart(List<String> cartItemsToRemove) {
        for (String cartItemToRemove : cartItemsToRemove) {
           removeItemToCart(cartItemToRemove);
        }
    }


    public List<String> getCartItemNames() {
        return getCartItems().stream()
                .map(cartItem -> cartItem.findElement(cartItemNames).getText())
                .toList();
    }

    public List<String> getCartItemDescriptions() {
        return getCartItems().stream()
                .map(cartItem -> cartItem.findElement(cartItemDescriptions).getText())
                .toList();
    }

    public List<String> getCartItemPrices( ) {
        return getCartItems().stream()
                .map(cartItem -> cartItem.findElement(cartItemPrices).getText())
                .toList();
    }

    public boolean isYourCartLabelDisplayed(){
        return isDisplayed(yourCartLabel);
    }


    public boolean isCartEmpty(){
      return waitForCartItemsToDisAppear();
    }

    public boolean isCartItemNameNotDisplayed(String cartItemName) {
        return isNotDisplayed(cartItemName(cartItemName));
    }


    public boolean isCartItemNameDisplayed(String cartItemName) {
        return isDisplayed(cartItemName(cartItemName));
    }

    public ProductPage clickContinueShoppingButton() {
        click(continueShoppingButton);
        ProductPage productPage = new ProductPage(driver);
        productPage.waitForProductsToAppear();
        return productPage;
    }

    public CheckoutInformationPage clickCheckoutButton() {
        click(checkoutButton);

        return new CheckoutInformationPage(driver);
    }


    public ProductDetailsPage clickCartName(String cartName){
        WebElement cartCard = getCartItem(cartName);
        click(cartCard.findElement(cartItemNames));

        return new ProductDetailsPage(driver);
    }

}
