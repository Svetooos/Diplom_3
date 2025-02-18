package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private final By HEADER_ENTER = By.xpath(".//h2[contains(text(), 'Вход')]");
    private final By EMAIL_FIELD = By.xpath(".//input[contains(@class, 'text') and @name='name']");
    private final By PASSWORD_FIELD = By.xpath(".//input[contains(@class, 'text') and @type='password']");
    private final By ENTER_BUTTON = By.xpath(".//button[text()='Войти']");
    private final By RESET_PASSWORD = By.xpath("//*[contains(text(),'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(HEADER_ENTER));
    }

    private void setEmail(String email) {
        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    private void setPassword(String password) {
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    private void clickButtonEnter() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(ENTER_BUTTON));
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step
    public boolean checkLoginButtonIsDisplayed() {
        waitLoginPage();
        return driver.findElement(ENTER_BUTTON).isDisplayed();
    }

    @Step
    public void setProfile(String email, String password) {
        waitLoginPage();
        setEmail(email);
        setPassword(password);
        clickButtonEnter();
    }

    @Step
    public void clickResetPassword() {
        waitLoginPage();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(RESET_PASSWORD));
        driver.findElement(RESET_PASSWORD).click();
    }
}