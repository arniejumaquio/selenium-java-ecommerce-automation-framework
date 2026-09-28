package io.github.arniejumaquio.framework.listeners;

import io.github.arniejumaquio.framework.driver.DriverManager;
import io.github.arniejumaquio.framework.utils.AllureUtils;
import io.github.arniejumaquio.framework.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);


    @Override
    public void onStart(ITestContext context) {
        log.info("Starting suite", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Start", result.getName());


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test Failed", result.getName(),result.getThrowable());


        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                String screenShotPath =  ScreenshotUtils.takeScreenShot(result.getName(),driver);
                AllureUtils.attachScreenshot(result.getName(),screenShotPath);

            } catch (IOException e) {
                log.error("Unable to capture or attach screenshot for test: {}", result.getName());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        log.warn("Test Skipped", result.getName());

    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finished suite",
                context.getName(),
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());

    }


}
