package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConfiguration {

    public WebDriver getWebDriver() {
        if ("true".equals(System.getProperty("test.browser.use_yandex"))) {
            return runYandex();
        } else {
            return runChrome();
        }
    }

    public WebDriver runChrome() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    public WebDriver runYandex() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.bin"));
        return new ChromeDriver(options);
    }
}