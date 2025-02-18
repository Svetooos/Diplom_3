package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    private final By LOGIN_BUTTON = By.xpath("//*[contains(text(),'Войти в аккаунт')]");
    private final By ACCOUNT_BUTTON = By.xpath("//*[contains(text(),'Личный Кабинет')]");
    private final By BURGER_HEADER = By.xpath(".h1[contains(@class, 'text')]");
    private final By BASKET_HEADER = By.xpath(".section[contains (@class, 'BurgerConstructor_basket')]");
    private final By BUNS_BUTTON = By.xpath(".span[text()='Булки']");
    private final By SAUCE_BUTTON = By.xpath(".span[text()='Соусы']");
    private final By FILLING_BUTTON = By.xpath(".span[text()='Начинки']");
    private final By BUNS_TAB = By.xpath(".span[text()='Булки']/parent::div");
    private final By SAUCE_TAB = By.xpath(".span[text()='Соусы']/parent::div");
    private final By FILLING_TAB = By.xpath(".span[text()='Начинки']/parent::div");
    private final By CURRENT_TAB = By.xpath(".div[contains(@class, 'current')]/child::span");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step
    public void clickSauce() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(SAUCE_BUTTON));
        driver.findElement(SAUCE_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.attributeContains(SAUCE_TAB, "class", "current"));
    }

    @Step
    public void clickBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(BUNS_BUTTON));
        driver.findElement(BUNS_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.attributeContains(BUNS_TAB, "class", "current"));
    }

    @Step
    public void clickFilling() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(FILLING_BUTTON));
        driver.findElement(FILLING_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.attributeContains(FILLING_TAB, "class", "current"));
    }

    @Step
    public void clickAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(ACCOUNT_BUTTON));
        driver.findElement(ACCOUNT_BUTTON).click();
    }

    @Step
    public String waitBurgerHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_HEADER));
        return driver.findElement(BURGER_HEADER).getText();
    }

    @Step
    public boolean openSitePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(BASKET_HEADER));
        return driver.findElement(BASKET_HEADER).isDisplayed();
    }

    @Step
    public String getCurrentTabText() {
        return driver.findElement(CURRENT_TAB).getText();
    }
}
