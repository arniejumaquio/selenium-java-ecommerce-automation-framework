package io.github.arniejumaquio.framework.base;

import io.github.arniejumaquio.framework.config.ConfigReader;
import io.github.arniejumaquio.framework.pages.CartPage;
import io.github.arniejumaquio.framework.pages.LoginPage;
import io.github.arniejumaquio.framework.utils.InteractionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public abstract class BasePage {

    protected WebDriver driver;
    protected final By menuIcon = By.id("react-burger-menu-btn");
    protected final By allItemsMenu = By.id("inventory_sidebar_link");
    protected final By aboutMenu = By.id("about_sidebar_link");
    protected final By logoutMenu = By.id("logout_sidebar_link");
    protected final By resetAppStateMenu = By.id("reset_sidebar_link");
    private final By cartIcon = By.id("shopping_cart_container");
    private final By cartCount = By.cssSelector("span[data-test='shopping-cart-badge']");

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage logout() {
        hoverAndClick(menuIcon);
        click(logoutMenu);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoginPageToAppear();
        return loginPage;
    }

    public CartPage clickCartIcon(){
        click(cartIcon);

        return new CartPage(driver);
    }

    public int getCartCount(){
       return Integer.parseInt( InteractionUtils.getText(cartCount));
    }

    public  void navigateTo(String url) {
        InteractionUtils.navigateTo(url);
    }


    public void pageRefresh() {
        InteractionUtils.pageRefresh();
    }

    public  void multiplePageRefresh(int numOfTimes) {
        InteractionUtils.multiplePageRefresh(numOfTimes);
    }

    public  void clickBackBrowserButton(){
        InteractionUtils.clickBackBrowserButton();
    }

    public  void clickForwardBrowserButton(){
        InteractionUtils.clickForwardBrowserButton();
    }

    protected int getElementCount(By locator) {
        return InteractionUtils.getElementCount(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return InteractionUtils.findElements(locator);
    }

    public static Set<String> getWindowHandles() {
        return InteractionUtils.getWindowHandles();
    }

    public boolean isMultipleTabDisplayed() {
        return InteractionUtils.isMultipleTabDisplayed();
    }

    protected void click(By locator) {
        InteractionUtils.click(locator);
    }

    protected void click(WebElement element) {
        InteractionUtils.click(element);
    }

    protected void click(By locator, int times) {
        InteractionUtils.click(locator, times);
    }

    protected void multipleClick(By locator, int numOfClick) {
        InteractionUtils.multipleClick(locator, numOfClick);
    }

    protected void multipleClick(WebElement element, int numOfClick) {
        InteractionUtils.multipleClick(element, numOfClick);
    }

    protected void jsClick(By locator) {
        InteractionUtils.jsClick(locator);
    }

    protected void jsClick(WebElement element) {
        InteractionUtils.jsClick(element);
    }

    protected void doubleClick(By locator) {
        InteractionUtils.doubleClick(locator);
    }

    protected void doubleClick(WebElement element) {
        InteractionUtils.doubleClick(element);
    }

    protected void rightClick(By locator) {
        InteractionUtils.rightClick(locator);
    }

    protected void rightClick(WebElement element) {
        InteractionUtils.rightClick(element);
    }

    protected void hover(By locator) {
        InteractionUtils.hover(locator);
    }

    protected void hover(WebElement element) {
        InteractionUtils.hover(element);
    }

    protected void hoverAndClick(By locator) {
        InteractionUtils.hoverAndClick(locator);
    }

    protected void hoverAndClick(WebElement element) {
        InteractionUtils.hoverAndClick(element);
    }

    protected void scrollIntoView(By locator) {
        InteractionUtils.scrollIntoView(locator);
    }

    protected void scrollIntoView(WebElement element) {
        InteractionUtils.scrollIntoView(element);
    }

    protected void scrollToTop() {
        InteractionUtils.scrollToTop();
    }

    protected void scrollToBottom() {
        InteractionUtils.scrollToBottom();
    }

    protected void dragAndDrop(By sourceLocator, By targetLocator) {
        InteractionUtils.dragAndDrop(sourceLocator, targetLocator);
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        InteractionUtils.dragAndDrop(source, target);
    }

    protected void dragAndDropByOffset(WebElement source, int xOffset, int yOffset) {
        InteractionUtils.dragAndDropByOffset(source, xOffset, yOffset);
    }

    protected void type(By locator, String text) {
        InteractionUtils.type(locator, text);
    }

    protected void type(WebElement element, String text) {
        InteractionUtils.type(element, text);
    }

    protected void clear(By locator) {
        InteractionUtils.clear(locator);
    }

    protected void clear(WebElement element) {
        InteractionUtils.clear(element);
    }

    protected void selectByVisibleText(By locator, String visibleText) {
        InteractionUtils.selectByVisibleText(locator, visibleText);
    }

    public static void selectByVisibleText(By locator, String[] visibleText) {
        InteractionUtils.selectByVisibleText(locator, visibleText);
    }

    protected void selectByValue(By locator, String value) {
        InteractionUtils.selectByValue(locator, value);
    }

    protected void selectByIndex(By locator, int index) {
        InteractionUtils.selectByIndex(locator, index);
    }

    protected String getSelectedOptionText(By locator) {
        return InteractionUtils.getSelectedOptionText(locator);
    }

    protected String getText(By locator) {
        return InteractionUtils.getText(locator);
    }

    protected String getText(WebElement element) {
        return InteractionUtils.getText(element);
    }

    protected String getAttribute(By locator, String attributeName) {
        return InteractionUtils.getAttribute(locator, attributeName);
    }

    protected boolean isDisplayed(By locator) {
        return InteractionUtils.isDisplayed(locator);
    }

    protected boolean isDisplayed(WebElement element) {
        return InteractionUtils.isDisplayed(element);
    }


    protected boolean isNotDisplayed(By locator) {
        return InteractionUtils.isNotDisplayed(locator);
    }

    protected boolean isNotDisplayed(WebElement element) {
        return InteractionUtils.isNotDisplayed(element);
    }

    protected boolean isElementsDisplayed(By locator) {
        return InteractionUtils.isElementsDisplayed(locator);
    }

    protected boolean isElementsNotDisplayed(By locator) {
        return InteractionUtils.isElementsNotDisplayed(locator);
    }


    protected boolean isEnabled(By locator) {
        return InteractionUtils.isEnabled(locator);
    }

    protected boolean isSelected(By locator) {
        return InteractionUtils.isSelected(locator);
    }

    public String getCurrentUrl() {
        return InteractionUtils.getCurrentUrl();
    }

    protected String getPageTitle() {
        return InteractionUtils.getPageTitle();
    }


    public  String removeDollarSign(String text){
        return InteractionUtils.removeDollarSign(text);
    }
}
