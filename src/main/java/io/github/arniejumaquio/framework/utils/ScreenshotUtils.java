package io.github.arniejumaquio.framework.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public final class ScreenshotUtils {

    private static final Path SCREENSHOT_DIR = Path.of("target", "screenshots");

    private ScreenshotUtils() {
    }

    public static String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        Path screenShotDestination =  Path.of(System.getProperty("user.dir"))
                .resolve(SCREENSHOT_DIR)
                .resolve(testCaseName + ".png");
        FileUtils.copyFile(screenShot, screenShotDestination.toFile());

        return screenShotDestination.toString();

    }


}
