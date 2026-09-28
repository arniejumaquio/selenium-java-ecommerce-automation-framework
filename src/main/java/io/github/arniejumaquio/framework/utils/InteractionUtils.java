package io.github.arniejumaquio.framework.utils;

import io.github.arniejumaquio.framework.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public final class InteractionUtils {

    private InteractionUtils() {
    }

    private static WebDriver driver() {
        return DriverManager.getDriver();
    }

    private static Actions actions() {
        return new Actions(driver());
    }

    private static JavascriptExecutor js() {
        return (JavascriptExecutor) driver();
    }

    private static Select select(By locator) {
        return new Select(WaitUtils.waitForElementToAppear(locator));
    }



    public static void navigateTo(String url) {
        driver().navigate().to(url);
    }

    public static void pageRefresh() {
        driver().navigate().refresh();
    }

    public static void multiplePageRefresh(int numOfTimes) {
        for(int i = 0; i < numOfTimes; i++) {
            driver().navigate().refresh();
        }
    }

    public static void clickBackBrowserButton(){
        driver().navigate().back();
    }

    public static void clickForwardBrowserButton(){
        driver().navigate().forward();
    }

    public static int getElementCount(By locator) {
        return WaitUtils.waitForElementsToAppear(locator).size();
    }

    public static List<WebElement> findElements(By locator) {
        return driver().findElements(locator);
    }

    public static Set<String> getWindowHandles() {
        return driver().getWindowHandles();
    }

    public static boolean isMultipleTabDisplayed() {
        return getWindowHandles().size() > 1;
    }

    public static String getCurrentUrl() {
        return driver().getCurrentUrl();
    }

    public static String getPageTitle() {
        return driver().getTitle();
    }

    public static void click(By locator) {
        WebElement element = WaitUtils.waitForElementToBeClickable(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            jsClick(locator);
        }
    }

    public static void click(WebElement element) {
        try {
            WaitUtils.waitForElementToBeClickable(element);
            element.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            jsClick(element);
        }
    }

    public static void click(By locator, int times) {
        for (int i = 0; i < times; i++) {
            click(locator);
        }
    }

    public static void multipleClick(By locator, int numOfClick) {
        for (int i = 0; i < numOfClick; i++) {
            if (findElements(locator).isEmpty()) {
                break;
            }
            try {
                click(locator);
            } catch (TimeoutException | StaleElementReferenceException e) {
                break;
            }
        }
    }

    public static void multipleClick(WebElement element, int numOfClick) {
        for (int i = 0; i < numOfClick; i++) {
            try {
                click(element);
            } catch (TimeoutException | StaleElementReferenceException e) {
                break;
            }
        }
    }

    public static void jsClick(By locator) {
        WebElement element = WaitUtils.waitForElementToAppear(locator);
        js().executeScript("arguments[0].click();", element);
    }

    public static void jsClick(WebElement element) {
        WaitUtils.waitForElementToBeClickable(element);
        js().executeScript("arguments[0].click();", element);
    }

    public static void doubleClick(By locator) {
        actions().doubleClick(WaitUtils.waitForElementToBeClickable(locator)).perform();
    }

    public static void doubleClick(WebElement element) {
        WaitUtils.waitForElementToBeClickable(element);
        actions().doubleClick(element).perform();
    }

    public static void rightClick(By locator) {
        actions().contextClick(WaitUtils.waitForElementToBeClickable(locator)).perform();
    }

    public static void rightClick(WebElement element) {
        WaitUtils.waitForElementToBeClickable(element);
        actions().contextClick(element).perform();
    }

    public static void hover(By locator) {
        actions().moveToElement(WaitUtils.waitForElementToAppear(locator)).perform();
    }

    public static void hover(WebElement element) {
        WaitUtils.waitForElementToAppear(element);
        actions().moveToElement(element).perform();
    }

    public static void hoverAndClick(By locator) {
        actions().moveToElement(WaitUtils.waitForElementToBeClickable(locator)).click().perform();
    }

    public static void hoverAndClick(WebElement element) {
        WaitUtils.waitForElementToAppear(element);
        actions().moveToElement(element).click().perform();
    }

    public static void scrollIntoView(By locator) {
        WebElement element = WaitUtils.waitForElementToAppear(locator);
        js().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollIntoView(WebElement element) {
        WaitUtils.waitForElementToAppear(element);
        js().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToTop() {
        js().executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom() {
        js().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = WaitUtils.waitForElementToAppear(sourceLocator);
        WebElement target = WaitUtils.waitForElementToAppear(targetLocator);
        actions().dragAndDrop(source, target).perform();
    }

    public static void dragAndDrop(WebElement source, WebElement target) {
        WaitUtils.waitForElementToBeClickable(source);
        WaitUtils.waitForElementToAppear(target);
        actions().dragAndDrop(source, target).perform();
    }

    public static void dragAndDropByOffset(WebElement source, int xOffset, int yOffset) {
        actions().clickAndHold(source).moveByOffset(xOffset, yOffset).release().perform();
    }

    public static void type(By locator, String text) {
        WebElement element = clear(locator);
        element.sendKeys(text);
    }

    public static void type(WebElement element, String text) {
        clear(element);
        element.sendKeys(text);
    }


    public static WebElement clear(By locator) {
        WebElement element = WaitUtils.waitForElementToAppear(locator);
        element.clear();
        return element;
    }

    public static void clear(WebElement element) {
        WaitUtils.waitForElementToAppear(element);
        element.clear();
    }

    public static void selectByVisibleText(By locator, String visibleText) {
        select(locator).selectByVisibleText(visibleText);
    }

    public static void selectByVisibleText(By locator, String[] visibleText) {
        for(int i = 0; i < visibleText.length; i++) {
            select(locator).selectByVisibleText(visibleText[i]);
        }
    }

    public static void selectByValue(By locator, String value) {
        select(locator).selectByValue(value);
    }

    public static void selectByIndex(By locator, int index) {
        select(locator).selectByIndex(index);
    }

    public static String getSelectedOptionText(By locator) {
        return select(locator).getFirstSelectedOption().getText();
    }

    public static String getText(By locator) {
        return WaitUtils.waitForElementToAppear(locator).getText();
    }

    public static String getText(WebElement element) {
        return WaitUtils.waitForElementToAppear(element).getText();
    }

    public static String getAttribute(By locator, String attributeName) {
        return WaitUtils.waitForElementToAppear(locator).getDomAttribute(attributeName);
    }

    public static boolean isDisplayed(By locator) {
        return WaitUtils.waitForElementToAppear(locator).isDisplayed();
    }

    public static boolean isDisplayed(WebElement element) {
        return WaitUtils.waitForElementToAppear(element).isDisplayed();
    }

    public static boolean isNotDisplayed(By locator) {
        return WaitUtils.waitForElementToDisappear(locator);
    }


    public static boolean isNotDisplayed(WebElement element) {
        return WaitUtils.waitForElementToDisappear(element);
    }
    public static boolean isElementsDisplayed(By locator) {
        return !WaitUtils.waitForElementsToAppear(locator).isEmpty();
    }

    public static boolean isElementsNotDisplayed(By locator) {
        return WaitUtils.waitForElementsToDisappear(locator);
    }

    public static boolean isEnabled(By locator) {
        return WaitUtils.waitForElementToAppear(locator).isEnabled();
    }

    public static boolean isSelected(By locator) {
        return WaitUtils.waitForElementToAppear(locator).isSelected();
    }

    //keyboard navigation
    public static void tab(By locator, Keys keys) {
        WebElement element = clear(locator);
        element.sendKeys(Keys.TAB);
    }

    public static void tab(WebElement element, Keys keys ){
        clear(element);
        element.sendKeys(keys);
    }


    //remove dollar sign
    public static String removeDollarSign(String text){
       return text.split("\\$")[1].trim();
    }

}
