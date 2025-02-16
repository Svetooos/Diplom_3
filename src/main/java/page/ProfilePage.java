package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;

    private final By EMAIL_FIELD = By.xpath(".//input[contains(@name, 'name') and @type='text']");
    private final By CONSTRUCTOR_BUTTON = By.xpath(".//p[contains(@class, 'AppHeader_header__linkText') and text()='Конструктор'] ");
    private final By LOGO_HEADER = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");
    private final By EXIT_BUTTON = By.xpath(".//button[contains(@class, 'Account_button')]");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public String getEmail() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
        return driver.findElement(EMAIL_FIELD).getAttribute("value");
    }

    @Step
    public void clickConstructor() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(CONSTRUCTOR_BUTTON));
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step
    public void clickLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(LOGO_HEADER));
        driver.findElement(LOGO_HEADER).click();
    }

    @Step
    public void clickExit() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(EXIT_BUTTON));
        driver.findElement(EXIT_BUTTON).click();
    }
}