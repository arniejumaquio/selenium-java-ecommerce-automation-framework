package io.github.arniejumaquio.framework.utils;

import io.github.arniejumaquio.framework.config.ConfigReader;
import io.github.arniejumaquio.framework.driver.DriverManager;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public final class WaitUtils {

    private static final int DEFAULT_TIMEOUT = ConfigReader.getInt("explicit.wait.timeout");

    private WaitUtils() {
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        return   new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        return   new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement  waitForElementToAppear(By locator) {
        return   new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement  waitForElementToAppear(WebElement element) {
        return   new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement>  waitForElementsToAppear(By locator) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
    }

    public static  boolean waitForElementToDisappear(By locator) {
        return   new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static  boolean waitForElementToDisappear(WebElement element) {
        return   new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.invisibilityOf(element));
    }


    public static  boolean waitForElementsToDisappear(By locator) {
        return   new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


}
