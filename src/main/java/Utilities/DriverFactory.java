package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

   // private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            tlDriver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            tlDriver.set(new FirefoxDriver());
        }
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        tlDriver.remove();
    }
}

