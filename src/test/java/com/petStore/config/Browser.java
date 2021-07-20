package com.petStore.config;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

    private static WebDriver driver;
    public static WebDriver getDriver() {

        if(driver==null || ((RemoteWebDriver)driver).getSessionId() == null) {

                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    return driver;

        }
        return driver;
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void quit(int sleepSeconds) {
        sleep(sleepSeconds);
        driver.quit();
    }

    public static void quit() {
        driver.quit();
    }

    public static void close() {
        driver.close();
    }

    public static void closeAndSwitchPrevious(WebDriver driver) {
        String handle = driver.getWindowHandle();
        String newWindowHandle="";
        Set<String> windowHandles = driver.getWindowHandles();
        driver.close();
        // capture the handle of the tab which is not the current tab
        for (String each : windowHandles) {
            if (!each.equals(handle)) {
                newWindowHandle = each;
            }
        }
        // switch to new window using the handle of the new window
        driver.switchTo().window(newWindowHandle);
    }
    public static void switchNextWindow(WebDriver driver) {
        String handle = driver.getWindowHandle();
        String newWindowHandle="";
        Set<String> windowHandles = driver.getWindowHandles();
        // capture the handle of the tab which is not the current tab
        for (String each : windowHandles) {
            if (!each.equals(handle)) {
                newWindowHandle = each;
            }
        }
        // switch to new window using the handle of the new window
        driver.switchTo().window(newWindowHandle);
    }
}
