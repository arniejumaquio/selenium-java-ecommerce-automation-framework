package io.github.arniejumaquio.framework.pages;

import io.github.arniejumaquio.framework.base.BasePage;
import io.github.arniejumaquio.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;

public class ProductPage extends BasePage {


    private final By productsLabel = By.xpath("//span[@data-test='title' and text()='Products']");
    private final By sortingDropdown = By.cssSelector("select[data-test='product-sort-container']");
    private final By products = By.cssSelector("div[data-test='inventory-item']");
    private final By productImages = By.cssSelector("img[data-test*='inventory-item']");
    private final By productNames = By.cssSelector("div[data-test='inventory-item-name']");
    private final By productDescriptions = By.cssSelector("div[data-test='inventory-item-desc']");
    private final By prices = By.cssSelector("div[data-test='inventory-item-price']");
    private final By addToCartButtons = By.cssSelector("button[data-test*='add']");
    private final By removeToCartButtons = By.cssSelector("button[data-test*='remove']");


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsLabelDisplayed(){
        return isDisplayed(productsLabel);
    }

    public void waitForProductsToAppear() {
        getProducts();
    }

    @Override
    public void multiplePageRefresh(int numOfTimes) {
        getProducts();
        super.multiplePageRefresh(numOfTimes);
    }

    public boolean waitForProductsToDisAppear() {
        return isElementsNotDisplayed(products);
    }

    private List<WebElement> getProducts(){
        WaitUtils.waitForElementToAppear(sortingDropdown);
        return WaitUtils.waitForElementsToAppear(products);
    }

    private WebElement getProduct(String productToSearch){
        WebElement productCard = getProducts().stream()
                .filter(product -> product.findElement(productNames).getText().equalsIgnoreCase(productToSearch))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product card not found" + productToSearch));

        return  productCard;
    }

    public void addToCart(List<String> productsToAdd){

        for(String productToAdd:productsToAdd){
            WebElement productCard = getProduct(productToAdd);
            click(productCard.findElement(addToCartButtons));
        }

    }

    public void removeToCart(List<String> productsToRemove){

        for(String productToRemove:productsToRemove) {
            WebElement productCard = getProduct(productToRemove);
            click(productCard.findElement(removeToCartButtons));
        }
    }

    public int getProductCount(){
        return getProducts().size();
    }

    public List<String> getProductNames(){
       return  getProducts().stream().map(products -> products.findElement(productNames).getText()).toList();

    }

    public List<Double> getProductPrices(){
        List<String> pricesInText = getProducts().stream().map(products -> products.findElement(prices).getText()).toList();
        return pricesInText.stream().map(priceInText -> Double.parseDouble(removeDollarSign(priceInText))).toList();

    }

    public List<String> getProductDescriptions(){
        return  getProducts().stream().map(products -> products.findElement(productDescriptions).getText()).toList();
    }

    public String getProductName(String productName){
        WebElement productCard = getProduct(productName);
        return getText(productCard.findElement(productNames));
    }

    public String getProductPrice(String productName){
        WebElement productCard = getProduct(productName);
        return getText(productCard.findElement(prices));
    }

    public String getProductDescription(String productName){
        WebElement productCard = getProduct(productName);
        return getText(productCard.findElement(productDescriptions));
    }



    public void selectSort(String option){
        selectByVisibleText(sortingDropdown,option);
    }

    public void selectSort(String[] option){
        selectByVisibleText(sortingDropdown,option);
    }


    public boolean isProductsDisplayed(){
       return isElementsDisplayed(products);
    }


    public boolean isProductImageDisplayed(String productName){
        WebElement productCard = getProduct(productName);
        return isDisplayed(productCard.findElement(productImages));
    }


    public boolean isProductNameDisplayed(String productName){
        WebElement productCard = getProduct(productName);
        return isDisplayed(productCard.findElement(productNames));
    }



    public boolean isAddToCartButtonDisplayed(String productName){
        WebElement productCard = getProduct(productName);
        return isDisplayed(productCard.findElement(addToCartButtons));
    }


    public ProductDetailsPage clickProductName(String productName){
        WebElement productCard = getProduct(productName);
        click(productCard.findElement(productNames));

        return new ProductDetailsPage(driver);
    }

    public ProductDetailsPage clickProductImage(String productName){
        WebElement productCard = getProduct(productName);
        click(productCard.findElement(productImages));

        return new ProductDetailsPage(driver);
    }




}
