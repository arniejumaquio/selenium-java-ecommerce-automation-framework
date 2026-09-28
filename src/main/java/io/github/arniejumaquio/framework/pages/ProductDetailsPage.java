package io.github.arniejumaquio.framework.pages;

import io.github.arniejumaquio.framework.base.BasePage;
import io.github.arniejumaquio.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private final By backToProductsButton = By.id("back-to-products");
    private final By productImage = By.cssSelector("img[data-test*='item']");
    private final By productName = By.cssSelector("div[data-test='inventory-item-name']");
    private final By productDescription = By.cssSelector("div[data-test='inventory-item-desc']");
    private final By price = By.cssSelector("div[data-test='inventory-item-price']");
    private final By addToCartButton = By.cssSelector("button[data-test*='add']");
    private final By removeToCartButton = By.cssSelector("button[data-test='remove']");

    public ProductDetailsPage(WebDriver driver){
        super(driver);
        WaitUtils.waitForElementToAppear(backToProductsButton);
    }


    public ProductPage clickBackToProductsButton(){
        click(backToProductsButton);

        return new ProductPage(driver);
    }

    public boolean isProductImageDisplayed(){
        return isDisplayed(productImage);
    }

    public String getProductName(){
        return getText(productName);
    }

    public String getProductDescription(){
        return getText(productDescription);
    }

    public String getPrice(){
        return getText(price);
    }

    public boolean isAddToCartButtonDisplayed(){
        return isDisplayed(addToCartButton);
    }

    public void clickAddToCartButton(){
        click(addToCartButton);
    }

    public void clickRemoveToCartButton(){
        click(removeToCartButton);
    }

}
