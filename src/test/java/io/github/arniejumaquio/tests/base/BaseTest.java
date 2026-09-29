package io.github.arniejumaquio.tests.base;

import io.github.arniejumaquio.framework.config.ConfigReader;
import io.github.arniejumaquio.framework.driver.DriverFactory;
import io.github.arniejumaquio.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public abstract class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String execution = ConfigReader.get("execution");
        String gridUrl = execution.equalsIgnoreCase("remote") ? ConfigReader.get("grid.url") : null;
        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBoolean("headless");
        log.info("Launching {} (execution={}, headless={})", browser, execution, headless);

        WebDriver driver = DriverFactory.createDriver(browser, headless, execution, gridUrl);
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getInt("page.load.timeout")));


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }


    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }


}
