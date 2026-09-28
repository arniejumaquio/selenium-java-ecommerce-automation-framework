package io.github.arniejumaquio.framework.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public final class DriverFactory {

    private static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browserName, boolean headless, String execution, String gridUrl) {

        if ("remote".equalsIgnoreCase(execution)) {
            return createRemoteDriver(browserName, headless, gridUrl);
        }
        if (!"local".equalsIgnoreCase(execution)) {
            throw new IllegalArgumentException("Unsupported execution mode: " + execution
                    + ". Expected local or remote.");
        }

        if (browserName.equals("chrome")) {

            return new ChromeDriver(createChromeOptions(headless));

        } else if (browserName.equals("firefox")) {

            return new FirefoxDriver(createFirefoxOptions(headless));

        } else if (browserName.equals("edge")) {

            return new EdgeDriver(createEdgeOptions(headless));

        } else if (browserName.equals("safari")) {

            return new SafariDriver();

        } else {

            System.out.println("Unsupported browser" + browserName);
            return null;

        }
    }

    private static WebDriver createRemoteDriver(String browserName, boolean headless, String gridUrl)  {

        Capabilities options;
        URL url = null;
        try {
            url = URI.create(gridUrl).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        if (browserName.equalsIgnoreCase("chrome")) {
            options = createChromeOptions(headless);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            options = createFirefoxOptions(headless);
        } else if (browserName.equalsIgnoreCase("edge")) {
            options = createEdgeOptions(headless);
        } else if (browserName.equalsIgnoreCase("safari")) {
            options = new SafariOptions();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        return new RemoteWebDriver(url, options);
    }


    private static ChromeOptions createChromeOptions(boolean headless) {

        ChromeOptions options = new ChromeOptions();

        //disable chrome breached password warning
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--window-size=1920,1080");

        if (headless) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        }

        return options;
    }
    private static FirefoxOptions createFirefoxOptions(boolean headless) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        if (headless) {
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;
    }

    private static EdgeOptions createEdgeOptions(boolean headless) {

        EdgeOptions options = new EdgeOptions();


        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--disable-features=PasswordLeakDetection");

        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
        } else {
            options.addArguments("--start-maximized");
        }

        return options;
    }

}
