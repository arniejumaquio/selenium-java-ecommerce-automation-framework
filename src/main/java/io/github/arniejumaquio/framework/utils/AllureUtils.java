package io.github.arniejumaquio.framework.utils;

import io.qameta.allure.Allure;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    private AllureUtils(){

    }

    public static void attachScreenshot(String screenshotName, String screenshotPath) throws IOException {

        Path path = Path.of(screenshotPath);

        try {
            InputStream inputStream = Files.newInputStream(path);
            Allure.addAttachment(screenshotName, "image/png", inputStream, ".png");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
